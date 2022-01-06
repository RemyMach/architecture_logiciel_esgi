package fr.remy.cc1.application.customer;

import fr.remy.cc1.kernel.event.Subscriber;
import fr.remy.cc1.domain.user.Users;

public class SubscriptionSuccessfulEventCustomerSubscription implements Subscriber<SubscriptionSuccessfulEvent> {

    private final Users users;

    public SubscriptionSuccessfulEventCustomerSubscription(Users users) {
        this.users = users;
    }

    @Override
    public void accept(SubscriptionSuccessfulEvent subscriptionSuccessfulEvent) {
        this.users.saveSubscriptionOffer(subscriptionSuccessfulEvent.getUserId(), subscriptionSuccessfulEvent.getSubscriptionOffer());
    }
}
