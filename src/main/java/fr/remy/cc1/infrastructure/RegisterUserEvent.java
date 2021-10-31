package fr.remy.cc1.infrastructure;

import fr.remy.cc1.domain.User;
import fr.remy.cc1.domain.event.Event;
import fr.remy.cc1.domain.event.EventId;

import java.time.ZonedDateTime;

public class RegisterUserEvent implements Event {

    private final EventId eventId;
    private final ZonedDateTime occurredDate;
    private final User user;

    private RegisterUserEvent(EventId eventId, ZonedDateTime occurredDate, User user) {
        this.eventId = eventId;
        this.occurredDate = occurredDate;
        this.user = user;
    }

    public static RegisterUserEvent withUser(User user) {
        return new RegisterUserEvent(EventId.create(), ZonedDateTime.now(), user);
    }

    @Override
    public EventId getId() {
        return eventId;
    }

    @Override
    public ZonedDateTime getOccurredDate() {
        return occurredDate;
    }

    public EventId getEventId() {
        return eventId;
    }

    public User getUser() {
        return user;
    }
}
