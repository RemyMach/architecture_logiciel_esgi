package fr.remy.cc1.infrastructure.mail;

import fr.remy.cc1.domain.event.Subscriber;
import fr.remy.cc1.domain.mail.Mail;
import fr.remy.cc1.infrastructure.payment.SubscriptionSuccessfulEvent;

public class SubscriptionSuccessfulEventMessengerSubscription implements Subscriber<SubscriptionSuccessfulEvent> {

    private final Mail mail;

    public SubscriptionSuccessfulEventMessengerSubscription(Mail mail) {
        this.mail = mail;
    }

    @Override
    public void accept(SubscriptionSuccessfulEvent subscriptionSuccessfulEvent) {
        System.out.println("on se sert du mailing pour dire que la subscription s'est bien éffectué");
    }
}
