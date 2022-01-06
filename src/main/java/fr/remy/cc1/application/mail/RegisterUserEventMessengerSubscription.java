package fr.remy.cc1.application.mail;

import fr.remy.cc1.application.UserDTO;
import fr.remy.cc1.domain.mail.Content;
import fr.remy.cc1.domain.mail.EmailSender;
import fr.remy.cc1.domain.mail.Message;
import fr.remy.cc1.domain.user.User;
import fr.remy.cc1.kernel.event.Subscriber;
import fr.remy.cc1.application.user.RegisterUserEvent;

public class RegisterUserEventMessengerSubscription implements Subscriber<RegisterUserEvent> {

    private final EmailSender emailSender;

    public RegisterUserEventMessengerSubscription(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public void accept(RegisterUserEvent registerUserEvent) {
        UserDTO userDTO = registerUserEvent.getUserDTO();
        this.sendMail(userDTO);
    }

    private void sendMail(UserDTO userDTO) {
        Message message = new Message(
                userDTO.email,
                "pomme@pomme.fr",
                "Welcome to our prenium e-market",
                Content.withText("welcome " + userDTO.email + ", we are really happy to see you in our application"));
        this.emailSender.send(message);
    }
}
