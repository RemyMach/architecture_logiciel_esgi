package fr.remy.cc1.domain.user;

import fr.remy.cc1.kernel.event.ApplicationEvent;
import fr.remy.cc1.kernel.event.Event;
import fr.remy.cc1.kernel.event.EventId;

import java.time.ZonedDateTime;

public class RegisterUserEvent implements Event, ApplicationEvent {

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
