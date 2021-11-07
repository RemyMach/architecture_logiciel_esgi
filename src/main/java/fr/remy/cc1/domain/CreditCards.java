package fr.remy.cc1.domain;

import fr.remy.cc1.domain.payment.CreditCardId;
import fr.remy.cc1.domain.user.User;

public interface CreditCards {
    void save(CreditCard creditCard, User user);

    CreditCardId nextIdentity();
}
