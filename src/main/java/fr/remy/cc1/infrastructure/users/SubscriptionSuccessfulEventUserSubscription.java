package fr.remy.cc1.infrastructure.users;

import fr.remy.cc1.domain.user.Users;
import fr.remy.cc1.domain.event.Subscriber;
import fr.remy.cc1.infrastructure.SubscriptionSuccessfulEvent;

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
