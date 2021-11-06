package fr.remy.cc1.domain;

import fr.remy.cc1.domain.event.Event;
import fr.remy.cc1.domain.event.EventBus;
import fr.remy.cc1.domain.payment.Payment;
import fr.remy.cc1.domain.user.User;
import fr.remy.cc1.infrastructure.payment.SubscriptionSuccessfulEvent;

public class PaymentService {

    private final Payment payment;

    private final EventBus<Event> eventBus;

    public PaymentService(Payment payment, EventBus eventBus) {
        this.payment = payment;
        this.eventBus = eventBus;
    }

    public void paySubscription(SubscriptionOffer subscriptionOffer, Currency currency, User user) {
        try {
            this.payment.start(subscriptionOffer.getAmount());
        } catch(Exception e) {
            // voir ce qu'on fait pour l'erreur
        }
        this.eventBus.send(SubscriptionSuccessfulEvent.of(user, subscriptionOffer, currency));
    }
}
