package fr.remy.cc1.infrastructure.payment;

import fr.remy.cc1.domain.User;
import fr.remy.cc1.domain.event.Event;
import fr.remy.cc1.domain.event.EventId;

import java.time.ZonedDateTime;

public class PaySubscriptionEvent implements Event {

    private final EventId eventId;
    private final ZonedDateTime occurredDate;
    private final User user;

    private PaySubscriptionEvent(EventId eventId, ZonedDateTime occurredDate, User user) {
        this.eventId = eventId;
        this.occurredDate = occurredDate;
        this.user = user;
    }

    public static PaySubscriptionEvent withUser(User user) {
        return new PaySubscriptionEvent(EventId.create(), ZonedDateTime.now(), user);
    }

    @Override
    public EventId getId() {
        return null;
    }

    @Override
    public ZonedDateTime getOccurredDate() {
        return null;
    }

    public EventId getEventId() {
        return eventId;
    }

    public User getUser() {
        return user;
    }
}
