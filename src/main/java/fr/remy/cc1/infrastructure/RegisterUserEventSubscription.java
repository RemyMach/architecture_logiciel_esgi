package fr.remy.cc1.infrastructure;

import fr.remy.cc1.domain.User;
import fr.remy.cc1.domain.event.Subscriber;
import fr.remy.cc1.domain.mail.Content;
import fr.remy.cc1.domain.mail.Mail;
import fr.remy.cc1.domain.mail.Message;
import fr.remy.cc1.domain.mail.UserMailSender;

public class RegisterUserEventSubscription implements Subscriber<RegisterUserEvent> {

    private final Mail mail;

    public RegisterUserEventSubscription(Mail mail) {
        this.mail = mail;
    }

    @Override
    public void accept(RegisterUserEvent registerUserEvent) {
        User user = registerUserEvent.getUser();
        Message message = new Message(
                user,
                "pomme@pomme.fr",
                "Welcome to our prenium e-market",
                Content.withText("welcome " + user.getEmail() + ", we are really happy to see you in our application"));
        mail.send(message);
    }
}
