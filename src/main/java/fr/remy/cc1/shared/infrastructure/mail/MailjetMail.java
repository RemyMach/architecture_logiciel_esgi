package fr.remy.cc1.shared.infrastructure.mail;

import fr.remy.cc1.shared.domain.mail.Mail;
import fr.remy.cc1.shared.domain.mail.Message;

public class MailjetMail implements Mail {

    @Override
    public void send(Message message) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }
}
