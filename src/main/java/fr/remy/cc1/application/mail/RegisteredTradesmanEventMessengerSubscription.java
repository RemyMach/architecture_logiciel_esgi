package fr.remy.cc1.application.mail;

import fr.remy.cc1.application.UserDTO;
import fr.remy.cc1.application.user.RegisterTradesmanEvent;
import fr.remy.cc1.domain.mail.Content;
import fr.remy.cc1.domain.mail.EmailSender;
import fr.remy.cc1.domain.mail.Message;
import fr.remy.cc1.domain.user.Email;
import fr.remy.cc1.kernel.event.Subscriber;

public class RegisteredTradesmanEventMessengerSubscription implements Subscriber<RegisterTradesmanEvent> {

    private final EmailSender emailSender;

    public RegisteredTradesmanEventMessengerSubscription(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public void accept(RegisterTradesmanEvent registerTradesmanEvent) {
        UserDTO userDTO = registerTradesmanEvent.getUserDTO();
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
