package fr.remy.cc1.subscription.domain.creditcard;

import fr.remy.cc1.member.domain.user.UserId;

public interface CreditCards {
    void save(CreditCard creditCard, UserId userId);

    CreditCardId nextIdentity();

    CreditCard byId(CreditCardId creditCardId);

    CreditCard findByUserId(UserId userId);

    void delete(CreditCardId creditCardId);
}
