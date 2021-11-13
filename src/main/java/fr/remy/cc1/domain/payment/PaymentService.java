package fr.remy.cc1.domain.payment;

import fr.remy.cc1.domain.customer.SubscriptionOffer;
import fr.remy.cc1.domain.user.User;
import fr.remy.cc1.domain.customer.SubscriptionSuccessfulEvent;
import fr.remy.cc1.infrastructure.user.UserCreationEventBus;

public class PaymentService {

    private final Payment payment;


    public PaymentService(Payment payment) {
        this.payment = payment;
    }

    public void paySubscription(SubscriptionOffer subscriptionOffer, Currency currency, User user) {

        this.payment.start(subscriptionOffer.getAmount());
        UserCreationEventBus.getInstance().send(SubscriptionSuccessfulEvent.of(user, subscriptionOffer, currency));
    }
}
