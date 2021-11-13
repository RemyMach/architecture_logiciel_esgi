package fr.remy.cc1.domain.mail;


public interface MockMail extends Mail {

        @Override
        public void send(Message message);

        public Integer getSendMailCount();
}
