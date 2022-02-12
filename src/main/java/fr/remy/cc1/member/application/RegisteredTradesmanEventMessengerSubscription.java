package fr.remy.cc1.member.application;

import fr.remy.cc1.domain.mail.Content;
import fr.remy.cc1.domain.mail.EmailSender;
import fr.remy.cc1.domain.mail.Message;
import fr.remy.cc1.member.domain.user.Email;
import fr.remy.cc1.kernel.event.Subscriber;

public class RegisteredTradesmanEventMessengerSubscription implements Subscriber<RegisteredTradesmanEvent> {

    private final EmailSender emailSender;

    public RegisteredTradesmanEventMessengerSubscription(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public void accept(RegisteredTradesmanEvent registeredTradesmanEvent) {
        UserDTO userDTO = registeredTradesmanEvent.getUserDTO();
        this.sendMail(userDTO);
    }

    private void sendMail(UserDTO userDTO) {
        Message message = new Message(
                userDTO.email,
                new Email("pomme@pomme.fr"),
                "Welcome to our prenium e-market",
                Content.withText("welcome " + userDTO.email + ", we are really happy to see you in our application, you can now go on User profil to complete your profil and start to be recruted"));
        this.emailSender.send(message);
    }
}
