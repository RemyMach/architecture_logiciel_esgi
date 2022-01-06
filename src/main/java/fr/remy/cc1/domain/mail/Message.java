package fr.remy.cc1.domain.mail;

import fr.remy.cc1.domain.user.User;

public final class Message {

    private final String recipient;

    private final String sender;

    private final String subject;

    private final Content content;

    public Message(String recipient, String sender, String subject, Content content) {
        this.recipient = recipient;
        this.sender = sender;
        this.subject = subject;
        this.content = content;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getSender() {
        return sender;
    }

    public String getSubject() {
        return subject;
    }

    public Content getContent() {
        return content;
    }
}
