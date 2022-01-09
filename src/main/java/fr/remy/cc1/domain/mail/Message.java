package fr.remy.cc1.domain.mail;

import fr.remy.cc1.domain.user.Email;

public final class Message {

    private final Email recipient;

    private final Email sender;

    private final String subject;

    private final Content content;

    public Message(Email recipient, Email sender, String subject, Content content) {
        this.recipient = recipient;
        this.sender = sender;
        this.subject = subject;
        this.content = content;
    }

    public Email getRecipient() {
        return recipient;
    }

    public Email getSender() {
        return sender;
    }

    public String getSubject() {
        return subject;
    }

    public Content getContent() {
        return content;
    }
}
