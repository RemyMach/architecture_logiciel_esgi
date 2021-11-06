package fr.remy.cc1.domain;

import fr.remy.cc1.domain.event.Event;
import fr.remy.cc1.domain.event.EventBus;
import fr.remy.cc1.domain.payment.Payment;
import fr.remy.cc1.infrastructure.RegisterUserEvent;
import fr.remy.cc1.infrastructure.payment.PaymentSuccessfulEvent;

import java.math.BigDecimal;

public class PaymentService {

    private final Payment payment;

    private final EventBus<Event> eventBus;

    public PaymentService(Payment payment, EventBus eventBus) {
        this.payment = payment;
        this.eventBus = eventBus;
    }

    public void verifyPayment(BigDecimal amount) {
        try {
            this.payment.start(amount);
        } catch(Exception e) {
            // voir ce qu'on fait pour l'erreur
        }
        this.eventBus.send(PaymentSuccessfulEvent.withUser());
    }
}
