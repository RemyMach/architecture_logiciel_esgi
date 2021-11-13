package fr.remy.cc1.domain.mail;

import fr.remy.cc1.domain.event.Subscriber;
import fr.remy.cc1.domain.user.User;
import fr.remy.cc1.domain.customer.SubscriptionSuccessfulEvent;

public class SubscriptionSuccessfulEventMessengerSubscription implements Subscriber<SubscriptionSuccessfulEvent> {

    private final EmailSender emailSender;

    public SubscriptionSuccessfulEventMessengerSubscription(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public void accept(SubscriptionSuccessfulEvent subscriptionSuccessfulEvent) {
        System.out.println("on se sert du mailing pour dire que la subscription s'est bien éffectué");
        this.sendMail(subscriptionSuccessfulEvent.getUser());
    }

    private void sendMail(User user) {
        Message message = new Message(
                user,
                "pomme@pomme.fr",
                "Subscription Payment validate",
                Content.withText("Hello " + user.getEmail() + ", this mail is here to confirm the validation of your payment"));
        this.emailSender.send(message);
    }
}
