package fr.remy.cc1.shared.infrastructure.mail;

import fr.remy.cc1.shared.domain.mail.Mail;
import fr.remy.cc1.shared.domain.mail.Message;

public class SandboxMail implements Mail {

    @Override
    public void send(Message message) {
        System.out.println("Sandbox is sending email to " + message.getRecipient());
        System.out.println("Subject -> " + message.getSubject());
    }
}
