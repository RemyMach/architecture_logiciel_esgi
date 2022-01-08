package fr.remy.cc1.domain.user;
import fr.remy.cc1.application.UserDTO;
import fr.remy.cc1.domain.customer.SubscriptionOffer;
import fr.remy.cc1.infrastructure.exceptions.NoSuchEntityException;

import java.util.List;

public interface Users {
    void save(User user);

    void saveSubscriptionOffer(UserId userId, SubscriptionOffer subscriptionOffer);

    User byId(UserId userId) throws NoSuchEntityException;

    User byEmailAndPassword(Email email, Password password) throws NoSuchEntityException;

    UserId nextIdentity();

    List<User> findAll();

    List<User> findAllByPaidSinceMoreThanCertainMonthAgo(int months);

    SubscriptionOffer getSubscriptionOffer(UserId userId);
}
