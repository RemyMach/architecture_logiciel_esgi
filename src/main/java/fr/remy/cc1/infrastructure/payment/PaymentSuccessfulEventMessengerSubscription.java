package fr.remy.cc1.infrastructure.payment;

import fr.remy.cc1.domain.event.Subscriber;
import fr.remy.cc1.domain.mail.Mail;

public class PaymentSuccessfulEventMessengerSubscription implements Subscriber<SubscriptionSuccessfulEvent> {

    private final Mail mail;

    public PaymentSuccessfulEventMessengerSubscription(Mail mail) {
        this.mail = mail;
    }

    @Override
    public void accept(SubscriptionSuccessfulEvent subscriptionSuccessfulEvent) {
        System.out.println("on se sert du mailing pour dire que la subscription s'est bien éffectué");
    }
}
