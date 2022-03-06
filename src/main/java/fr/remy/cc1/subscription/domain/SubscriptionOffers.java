package fr.remy.cc1.subscription.domain;

import fr.remy.cc1.shared.domain.User;
import fr.remy.cc1.shared.domain.UserId;
import fr.remy.cc1.shared.infrastructure.exceptions.NoSuchEntityException;
import fr.remy.cc1.subscription.domain.customer.SubscriptionOffer;
import fr.remy.cc1.subscription.domain.customer.SubscriptionOfferId;

import java.util.List;

public interface SubscriptionOffers {

    SubscriptionOfferId nextIdentity();

    void save(UserId userId, SubscriptionOffer subscriptionOffer);

    User userById(UserId userId) throws NoSuchEntityException;

    List<User> findAllByPaidSinceMoreThanCertainMonthAgo(int months);

    SubscriptionOffer findByUserId(UserId userId);
}
