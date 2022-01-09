package fr.remy.cc1.domain.payment.creditcard;

import fr.remy.cc1.domain.user.UserId;

public interface CreditCards {
    void save(CreditCard creditCard, UserId userId);

    CreditCardId nextIdentity();

    CreditCard byId(CreditCardId creditCardId);

    CreditCard findByUserId(UserId userId);

    void delete(CreditCardId creditCardId);
}
