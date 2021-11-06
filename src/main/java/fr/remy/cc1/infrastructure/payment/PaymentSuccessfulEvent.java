package fr.remy.cc1.infrastructure.payment;

import fr.remy.cc1.domain.User;
import fr.remy.cc1.domain.event.Event;
import fr.remy.cc1.domain.event.EventId;

import java.time.ZonedDateTime;

public class PaymentSuccessfulEvent implements Event {

    private final EventId eventId;
    private final ZonedDateTime occurredDate;

    private PaymentSuccessfulEvent(EventId eventId, ZonedDateTime occurredDate) {
        this.eventId = eventId;
        this.occurredDate = occurredDate;
    }

    public static PaymentSuccessfulEvent withUser() {
        return new PaymentSuccessfulEvent(EventId.create(), ZonedDateTime.now());
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
}
