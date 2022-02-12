package fr.remy.cc1.subscription.application;

import fr.remy.cc1.member.application.UserDTO;
import fr.remy.cc1.subscription.application.SubscriptionSuccessTerminatedEvent;
import fr.remy.cc1.domain.mail.Content;
import fr.remy.cc1.domain.mail.EmailSender;
import fr.remy.cc1.domain.mail.Message;
import fr.remy.cc1.member.domain.user.Email;
import fr.remy.cc1.kernel.event.Subscriber;

public class SubscriptionSuccessTerminatedEventMessengerSubscription implements Subscriber<SubscriptionSuccessTerminatedEvent> {

    private final EmailSender emailSender;

    public SubscriptionSuccessTerminatedEventMessengerSubscription(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public void accept(SubscriptionSuccessTerminatedEvent subscriptionSuccessTerminatedEvent) {
        this.sendMail(subscriptionSuccessTerminatedEvent.getUserDTO());
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
