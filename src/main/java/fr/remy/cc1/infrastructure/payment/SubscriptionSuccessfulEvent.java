package fr.remy.cc1.infrastructure.payment;

import fr.remy.cc1.domain.Currency;
import fr.remy.cc1.domain.SubscriptionOffer;
import fr.remy.cc1.domain.user.User;
import fr.remy.cc1.domain.event.Event;
import fr.remy.cc1.domain.event.EventId;

import java.time.ZonedDateTime;

public class SubscriptionSuccessfulEvent implements Event {

    private final EventId eventId;
    private final ZonedDateTime occurredDate;
    private final User user;
    private final SubscriptionOffer subscriptionOffer;
    private final Currency currency;

    private SubscriptionSuccessfulEvent(EventId eventId, ZonedDateTime occurredDate, User user, SubscriptionOffer subscriptionOffer, Currency currency) {
        this.eventId = eventId;
        this.occurredDate = occurredDate;
        this.user = user;
        this.subscriptionOffer = subscriptionOffer;
        this.currency = currency;
    }

    public static SubscriptionSuccessfulEvent of(User user, SubscriptionOffer subscriptionOffer, Currency currency) {
        return new SubscriptionSuccessfulEvent(EventId.create(), ZonedDateTime.now(), user, subscriptionOffer, currency);
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

    public SubscriptionOffer getSubscriptionOffer() {
        return subscriptionOffer;
    }

    public Currency getCurrency() {
        return currency;
    }
}
