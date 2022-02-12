package fr.remy.cc1.member.infrastructure.user;

import fr.remy.cc1.domain.User;
import fr.remy.cc1.domain.UserId;
import fr.remy.cc1.subscription.domain.customer.SubscriptionOffer;
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
    private final Map<UserId, SubscriptionOffer> userSubscriptionData;

    public InMemoryUsers(Map<UserId, User> usersData, Map<UserId, SubscriptionOffer> userSubscriptionData) {
        this.usersData = usersData;
        this.userSubscriptionData = userSubscriptionData;
    }

    @Override
    public void save(User user) {
        this.usersData.put(user.getUserId(), user);
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
    public SubscriptionOffer getSubscriptionOffer(UserId userId) {
        return userSubscriptionData.get(userId);
    }
}
