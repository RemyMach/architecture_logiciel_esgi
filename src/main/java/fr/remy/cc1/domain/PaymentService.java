package fr.remy.cc1.domain;

import fr.remy.cc1.domain.event.Event;
import fr.remy.cc1.domain.event.EventBus;
import fr.remy.cc1.domain.payment.Payment;
import fr.remy.cc1.domain.user.User;
import fr.remy.cc1.infrastructure.SubscriptionSuccessfulEvent;
import fr.remy.cc1.infrastructure.UserCreationEventBus;

public class PaymentService {

    private final Payment payment;


    public PaymentService(Payment payment) {
        this.payment = payment;
    }

    public void paySubscription(SubscriptionOffer subscriptionOffer, Currency currency, User user) {
        try {
            this.payment.start(subscriptionOffer.getAmount());
        } catch(Exception e) {
            // voir ce qu'on fait pour l'erreur
        }
        UserCreationEventBus.getInstance().send(SubscriptionSuccessfulEvent.of(user, subscriptionOffer, currency));
    }
}
