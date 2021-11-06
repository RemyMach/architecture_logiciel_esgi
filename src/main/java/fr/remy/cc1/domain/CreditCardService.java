package fr.remy.cc1.domain;

import fr.remy.cc1.domain.event.Event;
import fr.remy.cc1.domain.event.EventBus;
import fr.remy.cc1.domain.user.User;
import fr.remy.cc1.infrastructure.creditcards.SaveCreditCardEvent;
import fr.remy.cc1.infrastructure.payment.SubscriptionSuccessfulEvent;

public class CreditCardService {

    private final EventBus<Event> eventBus;

    public CreditCardService(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public void save(CreditCard creditCard, User user) {
        this.eventBus.send(SaveCreditCardEvent.of(creditCard, user));
    }
}
