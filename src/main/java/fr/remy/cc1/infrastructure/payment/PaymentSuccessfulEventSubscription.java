package fr.remy.cc1.infrastructure.payment;

import fr.remy.cc1.domain.Handler;
import fr.remy.cc1.domain.event.Subscriber;
import fr.remy.cc1.domain.mail.UserMailSender;
import fr.remy.cc1.domain.payment.ApproveTradesman;
import fr.remy.cc1.domain.payment.Contractor;
import fr.remy.cc1.domain.payment.Payment;
import fr.remy.cc1.domain.payment.PaymentProcess;

public class PaymentSuccessfulEventSubscription implements Subscriber<PaymentSuccessfulEvent> {

    private final UserMailSender mailSender;

    public PaymentSuccessfulEventSubscription(UserMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void accept(PaymentSuccessfulEvent paymentSuccessfulEvent) {
        System.out.println("on se sert du mailing pour dire que c'est bien envoyé");
        System.out.println("on crée une order");
    }
}
