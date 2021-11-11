package fr.remy.cc1.domain;

import fr.remy.cc1.domain.payment.CreditCard;
import fr.remy.cc1.domain.user.User;

public class CreditCardService {

    private final CreditCards creditCards;

    public CreditCardService(CreditCards creditCards) {
        this.creditCards = creditCards;
    }

    public void save(CreditCard creditCard, User user) {
        this.creditCards.save(creditCard, user);
    }
}
