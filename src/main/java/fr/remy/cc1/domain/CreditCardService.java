package fr.remy.cc1.domain;

import fr.remy.cc1.domain.event.Event;
import fr.remy.cc1.domain.event.EventBus;
import fr.remy.cc1.domain.user.User;
import fr.remy.cc1.infrastructure.creditcards.SaveCreditCardEvent;

public class CreditCardService {

    private final CreditCards creditCards;

    public CreditCardService(CreditCards creditCards) {
        this.creditCards = creditCards;
    }

    public void save(CreditCard creditCard, User user) {
        this.creditCards.save(creditCard, user);
    }
}
