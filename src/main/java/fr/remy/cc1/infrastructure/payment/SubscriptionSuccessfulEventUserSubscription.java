package fr.remy.cc1.infrastructure.payment;

import fr.remy.cc1.domain.Users;
import fr.remy.cc1.domain.event.Subscriber;

public class SubscriptionSuccessfulEventUserSubscription implements Subscriber<SubscriptionSuccessfulEvent> {

    private final Users users;

    public SubscriptionSuccessfulEventUserSubscription(Users users) {
        this.users = users;
    }

    @Override
    public void accept(SubscriptionSuccessfulEvent subscriptionSuccessfulEvent) {
        System.out.println("on enregistre la subscription");
        this.users.saveSubscriptionOffer(subscriptionSuccessfulEvent.getUser(), subscriptionSuccessfulEvent.getSubscriptionOffer());
    }
}
