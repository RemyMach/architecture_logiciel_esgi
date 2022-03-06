package fr.remy.cc1.member.domain.user;

import fr.remy.cc1.shared.domain.User;
import fr.remy.cc1.shared.domain.UserId;
import fr.remy.cc1.shared.infrastructure.exceptions.NoSuchEntityException;
import fr.remy.cc1.subscription.domain.customer.SubscriptionOffer;

import java.util.List;

public interface Users {
    void save(User user);

    void saveSubscriptionOffer(UserId userId, SubscriptionOffer subscriptionOffer);

    User byId(UserId userId) throws NoSuchEntityException;

    User byEmailAndPassword(Email email, Password password) throws NoSuchEntityException;

    UserId nextIdentity();

    List<User> findAll();

    SubscriptionOffer getSubscriptionOffer(UserId userId);
}
