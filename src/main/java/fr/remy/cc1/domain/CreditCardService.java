package fr.remy.cc1.domain;

import fr.remy.cc1.domain.event.Event;
import fr.remy.cc1.domain.event.EventBus;

public class CreditCardService {
    private final CreditCards creditCards;

    private final EventBus<Event> eventBus;

    public CreditCardService(CreditCards creditCards,EventBus eventBus) {
        this.creditCards = creditCards;
        this.eventBus = eventBus;
    }

    public void create(CreditCard creditCard) {
        this.creditCards.save(creditCard);
    }
}
