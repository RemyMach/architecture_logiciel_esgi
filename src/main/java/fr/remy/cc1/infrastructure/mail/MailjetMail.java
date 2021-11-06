package fr.remy.cc1.infrastructure.mail;

import fr.remy.cc1.domain.mail.Mail;
import fr.remy.cc1.domain.mail.Message;

public class MailjetMail implements Mail {

    @Override
    public void send(Message message) {
        System.out.println("Mailjet is sending email to " + message.getRecipient().getEmail());
    }
}
