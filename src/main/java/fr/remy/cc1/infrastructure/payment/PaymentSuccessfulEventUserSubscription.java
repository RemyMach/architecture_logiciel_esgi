package fr.remy.cc1.infrastructure.payment;

import fr.remy.cc1.domain.Users;
import fr.remy.cc1.domain.event.Subscriber;

public class PaymentSuccessfulEventUserSubscription implements Subscriber<SubscriptionSuccessfulEvent> {

    private final Users users;

    public PaymentSuccessfulEventUserSubscription(Users users) {
        this.users = users;
    }

    @Override
    public void accept(SubscriptionSuccessfulEvent subscriptionSuccessfulEvent) {
        this.users.saveSubscriptionOffer(subscriptionSuccessfulEvent.getUser(), subscriptionSuccessfulEvent.getSubscriptionOffer());
    }
}
