package fr.remy.cc1.domain.mail;

public interface MailSender<E extends Object> {

    void sendMail(E e);
}
