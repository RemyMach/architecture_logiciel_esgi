package fr.remy.cc1.shared.domain.mail;

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
