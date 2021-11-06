package fr.remy.cc1.infrastructure.creditcards;

import fr.remy.cc1.domain.CreditCard;
import fr.remy.cc1.domain.event.Event;
import fr.remy.cc1.domain.event.EventId;
import fr.remy.cc1.domain.user.User;
import java.time.ZonedDateTime;

public class SaveCreditCardEvent implements Event {
    private final EventId eventId;
    private final ZonedDateTime occurredDate;
    private final CreditCard creditCard;
    private final User user;

    private SaveCreditCardEvent(EventId eventId, ZonedDateTime occurredDate, CreditCard creditCard, User user) {
        this.eventId = eventId;
        this.occurredDate = occurredDate;
        this.creditCard = creditCard;
        this.user = user;
    }

    public static SaveCreditCardEvent of(CreditCard creditCard, User user) {
        return new SaveCreditCardEvent(EventId.create(), ZonedDateTime.now(), creditCard, user);
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

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public User getUser() {
        return user;
    }
}
