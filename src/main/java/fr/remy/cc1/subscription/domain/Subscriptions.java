package fr.remy.cc1.subscription.domain;

import fr.remy.cc1.infrastructure.exceptions.NoSuchEntityException;
import fr.remy.cc1.subscription.domain.customer.SubscriptionOffer;
import fr.remy.cc1.domain.User;
import fr.remy.cc1.domain.UserId;

import java.util.List;

public interface Subscriptions {

    void save(UserId userId, SubscriptionOffer subscriptionOffer);

    User userById(UserId userId) throws NoSuchEntityException;

    List<User> findAllByPaidSinceMoreThanCertainMonthAgo(int months);

    SubscriptionOffer getSubscriptionOffer(UserId userId);
}
