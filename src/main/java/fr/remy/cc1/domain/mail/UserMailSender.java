package fr.remy.cc1.domain.mail;

import fr.remy.cc1.domain.User;

public class UserMailSender {
    public void sendMail(User user) {
        System.out.println("a mail has been send to " + user.getEmail());
    }
}
