package fr.remy.cc1.subscription.infrastructure.subscriptions;

import fr.remy.cc1.infrastructure.exceptions.InfrastructureExceptionsDictionary;
import fr.remy.cc1.infrastructure.exceptions.NoSuchEntityException;
import fr.remy.cc1.subscription.domain.customer.SubscriptionOffer;
import fr.remy.cc1.domain.User;
import fr.remy.cc1.domain.UserId;
import fr.remy.cc1.subscription.domain.PaymentState;
import fr.remy.cc1.subscription.domain.SubscriptionOffers;
import fr.remy.cc1.subscription.domain.customer.SubscriptionOfferId;
import fr.remy.cc1.subscription.domain.invoice.Invoice;
import fr.remy.cc1.subscription.domain.invoice.InvoiceId;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class InMemorySubscriptionOffers implements SubscriptionOffers {

    private final AtomicInteger counter = new AtomicInteger(0);
    private final Map<UserId, SubscriptionOffer> userSubscriptionData;
    private final Map<UserId, User> usersData;
    private final Map<SubscriptionOfferId, List<Invoice>> subscriptionInvoicesData;

    public InMemorySubscriptionOffers(Map<UserId, User> usersData, Map<UserId, SubscriptionOffer> userSubscriptionData, Map<SubscriptionOfferId, List<Invoice>> subscriptionInvoicesData) {
        this.usersData = usersData;
        this.userSubscriptionData = userSubscriptionData;
        this.subscriptionInvoicesData = subscriptionInvoicesData;
    }

    @Override
    public SubscriptionOfferId nextIdentity()  {
        return SubscriptionOfferId.of(counter.incrementAndGet());
    }

    @Override
    public void save(UserId userId, SubscriptionOffer subscriptionOffer) {
        this.userSubscriptionData.put(userId, subscriptionOffer);
    }

    @Override
    public User userById(UserId userId) throws NoSuchEntityException {
        final User user = usersData.get(userId);
        if (user == null) {
            throw new NoSuchEntityException(InfrastructureExceptionsDictionary.USER_NOT_FOUND.getErrorCode(), InfrastructureExceptionsDictionary.USER_NOT_FOUND.getMessage());
        }
        return user;
    }

    @Override
    public List<User> findAllByPaidSinceMoreThanCertainMonthAgo(int months) {
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        ZonedDateTime thresholdZoneDateTime = zonedDateTime.minusMonths(months);
        return List.copyOf(usersData.values().stream().filter( user -> {
            SubscriptionOffer subscriptionOffer = this.findByUserId(user.getUserId());
            if(subscriptionOffer == null) return false;
            List<Invoice> InvoiceList = subscriptionInvoicesData.get(subscriptionOffer.getSubscriptionOfferId());
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
    public SubscriptionOffer findByUserId(UserId userId) {
        return this.userSubscriptionData.get(userId);
    }
}
