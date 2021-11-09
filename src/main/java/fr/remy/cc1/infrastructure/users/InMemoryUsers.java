package fr.remy.cc1.infrastructure.users;

import fr.remy.cc1.domain.SubscriptionOffer;
import fr.remy.cc1.domain.user.User;
import fr.remy.cc1.domain.user.UserId;
import fr.remy.cc1.domain.user.Users;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class InMemoryUsers implements Users {

    private final AtomicInteger counter = new AtomicInteger(0);
    private final Map<UserId, User> usersData = new ConcurrentHashMap<>();
    private final Map<UserId, SubscriptionOffer> userSubscriptionData = new ConcurrentHashMap<>();

    @Override
    public void save(User user) {
        usersData.put(user.getUserId(), user);
    }

    @Override
    public void saveSubscriptionOffer(User user, SubscriptionOffer subscriptionOffer) {
        this.userSubscriptionData.put(user.getUserId(), subscriptionOffer);
    }

    @Override
    public User byId(UserId userId) {
        final User user = usersData.get(userId);
        if (user == null) {
            throw new RuntimeException("No user for " + userId.getValue());
        }
        return user;
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
