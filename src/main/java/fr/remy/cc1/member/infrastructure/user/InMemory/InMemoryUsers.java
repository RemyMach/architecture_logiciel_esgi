package fr.remy.cc1.member.infrastructure.user.InMemory;

import fr.remy.cc1.member.domain.customer.SubscriptionOffer;
import fr.remy.cc1.subscription.domain.invoice.Invoice;
import fr.remy.cc1.subscription.domain.PaymentState;
import fr.remy.cc1.member.domain.user.*;
import fr.remy.cc1.infrastructure.exceptions.InfrastructureExceptionsDictionary;
import fr.remy.cc1.infrastructure.exceptions.NoSuchEntityException;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class InMemoryUsers implements Users {

    private final AtomicInteger counter = new AtomicInteger(0);
    private final Map<UserId, User> usersData;
    private final Map<UserId, SubscriptionOffer> userSubscriptionData = new ConcurrentHashMap<>();

    public InMemoryUsers(Map<UserId, User> usersData) {
        this.usersData = usersData;
    }

    @Override
    public void save(User user) {
        usersData.put(user.getUserId(), user);
    }

    @Override
    public void saveSubscriptionOffer(UserId userId, SubscriptionOffer subscriptionOffer) {
        this.userSubscriptionData.put(userId, subscriptionOffer);
    }

    @Override
    public User byId(UserId userId) throws NoSuchEntityException {
        final User user = usersData.get(userId);
        if (user == null) {
            throw new NoSuchEntityException(InfrastructureExceptionsDictionary.USER_NOT_FOUND.getErrorCode(), InfrastructureExceptionsDictionary.USER_NOT_FOUND.getMessage());
        }
        return user;
    }

    @Override
    public User byEmailAndPassword(Email email, Password password) throws NoSuchEntityException {
        return usersData.values().stream()
                .findFirst()
                .filter( user -> {

                    return user.getEmail().equals(email) && user.getPassword().equals(password);
                })
                .orElseThrow(() -> new NoSuchEntityException(InfrastructureExceptionsDictionary.USER_NOT_FOUND.getErrorCode(), InfrastructureExceptionsDictionary.USER_NOT_FOUND.getMessage()));
    }

    @Override
    public UserId nextIdentity() {
        return UserId.of(counter.incrementAndGet());
    }

    @Override
    public List<User> findAll() {
        return usersData.values().stream().collect(Collectors.toList());
    }

    @Override
    public List<User> findAllByPaidSinceMoreThanCertainMonthAgo(int months) {
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        ZonedDateTime thresholdZoneDateTime = zonedDateTime.minusMonths(months);
        return List.copyOf(usersData.values().stream().filter( user -> {
            SubscriptionOffer subscriptionOffer = this.getSubscriptionOffer(user.getUserId());
            if(subscriptionOffer == null) return false;
            List<Invoice> InvoiceList = subscriptionOffer.getInvoiceList();
            if(InvoiceList.size() > 0) {
                int i = 0;
                while(i >= 0 && InvoiceList.get(i).getPaymentState() == PaymentState.REJECTED) {

                    i--;
                }
                if(i == -1) return false;
                Invoice invoice = InvoiceList.get(i);
                Instant thresholdInstantTime = thresholdZoneDateTime.toInstant().truncatedTo( ChronoUnit.DAYS );
                Instant lastInvoiceInstantTime = invoice.getCreateAt().toInstant().truncatedTo( ChronoUnit.DAYS );
                if(invoice.getPaymentState() == PaymentState.REJECTED) return false;
                if(lastInvoiceInstantTime.isBefore(thresholdInstantTime) || lastInvoiceInstantTime.equals(thresholdInstantTime)) {
                    return true;
                }
            }
            return false;
        }).collect(Collectors.toList()));
    }

    @Override
    public SubscriptionOffer getSubscriptionOffer(UserId userId) {
        return userSubscriptionData.get(userId);
    }
}
