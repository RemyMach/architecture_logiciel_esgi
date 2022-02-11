package fr.remy.cc1.application.mail;

import fr.remy.cc1.application.UserDTO;
import fr.remy.cc1.subscription.application.SubscriptionPaymentFailedEvent;
import fr.remy.cc1.domain.mail.Content;
import fr.remy.cc1.domain.mail.EmailSender;
import fr.remy.cc1.domain.mail.Message;
import fr.remy.cc1.member.domain.user.Email;
import fr.remy.cc1.kernel.event.Subscriber;

public class SubscriptionPaymentFailedEventMessengerSubscription implements Subscriber<SubscriptionPaymentFailedEvent> {

    private final EmailSender emailSender;

    public SubscriptionPaymentFailedEventMessengerSubscription(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public void accept(SubscriptionPaymentFailedEvent subscriptionPaymentFailedEvent) {
        this.sendMail(subscriptionPaymentFailedEvent.getUserDTO());
    }

    private void sendMail(UserDTO userDTO) {
        Message message = new Message(
                userDTO.email,
                new Email("pomme@pomme.fr"),
                "Your payment has failed",
                Content.withText("welcome " + userDTO.email + ", we are really happy to see your interest for our site but your payment has failed"));
        this.emailSender.send(message);
    }
}
