package fr.remy.cc1.infrastructure.mail;

import fr.remy.cc1.domain.user.User;
import fr.remy.cc1.domain.event.Subscriber;
import fr.remy.cc1.domain.mail.Content;
import fr.remy.cc1.domain.mail.Mail;
import fr.remy.cc1.domain.mail.Message;
import fr.remy.cc1.infrastructure.RegisterUserEvent;

public class RegisterUserEventMessengerSubscription implements Subscriber<RegisterUserEvent> {

    private final EmailSender emailSender;

    public RegisterUserEventMessengerSubscription(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public void accept(RegisterUserEvent registerUserEvent) {
        User user = registerUserEvent.getUser();
        this.sendMail(user);
    }

    private void sendMail(User user) {
        Message message = new Message(
                user,
                "pomme@pomme.fr",
                "Welcome to our prenium e-market",
                Content.withText("welcome " + user.getEmail() + ", we are really happy to see you in our application"));
        this.emailSender.send(message);
    }
}
