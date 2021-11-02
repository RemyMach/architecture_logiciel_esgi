package fr.remy.cc1.infrastructure;

import fr.remy.cc1.domain.User;
import fr.remy.cc1.domain.event.Subscriber;
import fr.remy.cc1.domain.mail.UserMailSender;

public class RegisterUserEventSubscription implements Subscriber<RegisterUserEvent> {

    private final UserMailSender mailSender;

    public RegisterUserEventSubscription(UserMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void accept(RegisterUserEvent registerUserEvent) {
        User user = registerUserEvent.getUser();
        mailSender.sendMail(user);
    }
}
