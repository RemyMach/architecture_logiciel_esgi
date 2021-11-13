package fr.remy.cc1.domain.customer;

import fr.remy.cc1.domain.event.Subscriber;
import fr.remy.cc1.domain.user.Users;

public class SubscriptionSuccessfulEventCustomerSubscription implements Subscriber<SubscriptionSuccessfulEvent> {

    private final Users users;

    public SubscriptionSuccessfulEventCustomerSubscription(Users users) {
        this.users = users;
    }

    @Override
    public void accept(SubscriptionSuccessfulEvent subscriptionSuccessfulEvent) {
        this.users.saveSubscriptionOffer(subscriptionSuccessfulEvent.getUser(), subscriptionSuccessfulEvent.getSubscriptionOffer());
    }
}
