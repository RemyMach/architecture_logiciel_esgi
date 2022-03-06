package fr.remy.cc1.subscription.application;

import fr.remy.cc1.kernel.event.Subscriber;
import fr.remy.cc1.member.application.UserDTO;
import fr.remy.cc1.member.domain.user.Email;
import fr.remy.cc1.shared.domain.mail.Content;
import fr.remy.cc1.shared.domain.mail.EmailSender;
import fr.remy.cc1.shared.domain.mail.Message;

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
