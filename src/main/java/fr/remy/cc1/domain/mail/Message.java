package fr.remy.cc1.domain.mail;

import fr.remy.cc1.domain.User;

public class Message {

    private final User recipient;

    private final String sender;

    private final String subject;

    private final Content content;

    public Message(User recipient, String sender, String subject, Content content) {
        this.recipient = recipient;
        this.sender = sender;
        this.subject = subject;
        this.content = content;
    }
}
