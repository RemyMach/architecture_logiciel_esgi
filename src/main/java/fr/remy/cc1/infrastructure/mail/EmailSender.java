package fr.remy.cc1.infrastructure.mail;

import fr.remy.cc1.domain.mail.Mail;
import fr.remy.cc1.domain.mail.Message;

public class EmailSender {

    private static EmailSender emailSenderInstance;

    private Mail mail;

    private void EmailSender() { }

    public static EmailSender getInstance() {
        if (emailSenderInstance == null) {
            emailSenderInstance = new EmailSender();
        }
        return emailSenderInstance;
    }

    public void setMail(Mail mail) {
        this.mail = mail;
    }

    public void send(Message message) {
        this.mail.send(message);
    }
}
