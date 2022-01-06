package fr.remy.cc1.application.customer;

import fr.remy.cc1.application.UserDTO;
import fr.remy.cc1.domain.customer.SubscriptionOffer;
import fr.remy.cc1.domain.payment.Currency;
import fr.remy.cc1.domain.payment.Money;
import fr.remy.cc1.domain.user.UserId;
import fr.remy.cc1.kernel.event.ApplicationEvent;
import fr.remy.cc1.kernel.event.Event;
import fr.remy.cc1.kernel.event.EventId;
import fr.remy.cc1.domain.user.User;

import java.time.ZonedDateTime;

public class SubscriptionSuccessfulEvent implements Event, ApplicationEvent {

    private final EventId eventId;
    private final ZonedDateTime occurredDate;
    private final UserId userId;
    private final UserDTO userDTO;
    private final SubscriptionOffer subscriptionOffer;
    private final Money money;

    private SubscriptionSuccessfulEvent(EventId eventId, ZonedDateTime occurredDate, UserId userId, SubscriptionOffer subscriptionOffer, Money money, UserDTO userDTO) {
        this.eventId = eventId;
        this.occurredDate = occurredDate;
        this.userId = userId;
        this.subscriptionOffer = subscriptionOffer;
        this.money = money;
        this.userDTO = userDTO;
    }


    public static SubscriptionSuccessfulEvent of(UserId userId, SubscriptionOffer subscriptionOffer, Money money, UserDTO userDTO) {
        return new SubscriptionSuccessfulEvent(EventId.create(), ZonedDateTime.now(), userId, subscriptionOffer, money, userDTO);
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
