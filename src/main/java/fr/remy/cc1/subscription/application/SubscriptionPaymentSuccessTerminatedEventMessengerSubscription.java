package fr.remy.cc1.subscription.application;

import fr.remy.cc1.kernel.event.Subscriber;
import fr.remy.cc1.member.application.UserDTO;
import fr.remy.cc1.member.domain.user.Email;
import fr.remy.cc1.shared.domain.mail.Content;
import fr.remy.cc1.shared.domain.mail.EmailSender;
import fr.remy.cc1.shared.domain.mail.Message;

public class SubscriptionPaymentSuccessTerminatedEventMessengerSubscription implements Subscriber<SubscriptionPaymentSuccessTerminatedEvent> {

    private final EmailSender emailSender;

    public SubscriptionPaymentSuccessTerminatedEventMessengerSubscription(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public void accept(SubscriptionPaymentSuccessTerminatedEvent subscriptionPaymentSuccessTerminatedEvent) {
        this.sendMail(subscriptionPaymentSuccessTerminatedEvent.getUserDTO());
    }

    private void sendMail(UserDTO userDTO) {
        Message message = new Message(
                userDTO.email,
                new Email("pomme@pomme.fr"),
                "Subscription Payment validate",
                Content.withText("Hello " + userDTO.email + ", this mail is here to confirm the validation of your payment"));
        this.emailSender.send(message);
    }
}
