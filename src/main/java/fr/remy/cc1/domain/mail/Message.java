package fr.remy.cc1.domain.mail;

import fr.remy.cc1.member.domain.user.Email;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(recipient, message.recipient) && Objects.equals(sender, message.sender) && Objects.equals(subject, message.subject) && Objects.equals(content, message.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipient, sender, subject, content);
    }
}
