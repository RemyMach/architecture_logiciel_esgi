package fr.remy.cc1.subscription.application;

import fr.remy.cc1.domain.UserId;
import fr.remy.cc1.kernel.event.ApplicationEvent;
import fr.remy.cc1.kernel.event.Event;
import fr.remy.cc1.kernel.event.EventId;
import fr.remy.cc1.member.application.UserDTO;
import fr.remy.cc1.subscription.domain.Money;
import fr.remy.cc1.subscription.domain.customer.SubscriptionOffer;

import java.time.ZonedDateTime;

public class SubscriptionSuccessTerminatedEvent implements Event, ApplicationEvent {

    private final EventId eventId;
    private final ZonedDateTime occurredDate;
    private final UserId userId;
    private final UserDTO userDTO;
    private final SubscriptionOffer subscriptionOffer;
    private final Money money;

    private SubscriptionSuccessTerminatedEvent(EventId eventId, ZonedDateTime occurredDate, UserId userId, SubscriptionOffer subscriptionOffer, Money money, UserDTO userDTO) {
        this.eventId = eventId;
        this.occurredDate = occurredDate;
        this.userId = userId;
        this.subscriptionOffer = subscriptionOffer;
        this.money = money;
        this.userDTO = userDTO;
    }


    public static SubscriptionSuccessTerminatedEvent of(UserId userId, SubscriptionOffer subscriptionOffer, Money money, UserDTO userDTO) {
        return new SubscriptionSuccessTerminatedEvent(EventId.create(), ZonedDateTime.now(), userId, subscriptionOffer, money, userDTO);
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

    public UserId getUserId() {
        return userId;
    }

    public SubscriptionOffer getSubscriptionOffer() {
        return subscriptionOffer;
    }

    public Money getMoney() {
        return money;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }
}
