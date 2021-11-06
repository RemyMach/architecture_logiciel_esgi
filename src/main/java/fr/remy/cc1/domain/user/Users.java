package fr.remy.cc1.domain.user;
import fr.remy.cc1.domain.SubscriptionOffer;

import java.util.List;

public interface Users {
    void save(User user);

    void saveSubscriptionOffer(User user, SubscriptionOffer subscriptionOffer);

    User byId(UserId userId);

    UserId nextIdentity();

    List<User> findAll();
}
