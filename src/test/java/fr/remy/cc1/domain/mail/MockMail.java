package fr.remy.cc1.domain.mail;

import fr.remy.cc1.infrastructure.mail.EmailSender;

public interface MockMail extends Mail {

        @Override
        public void send(Message message);

        public Integer getSendMailCount();
}
