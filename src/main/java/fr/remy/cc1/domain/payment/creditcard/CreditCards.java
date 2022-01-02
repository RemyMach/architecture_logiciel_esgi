package fr.remy.cc1.domain.payment.creditcard;

import fr.remy.cc1.domain.payment.creditcard.CreditCard;
import fr.remy.cc1.domain.payment.creditcard.CreditCardId;
import fr.remy.cc1.domain.user.User;
import fr.remy.cc1.domain.user.UserId;

public interface CreditCards {
    void save(CreditCard creditCard, User user);

    CreditCardId nextIdentity();

    CreditCard byId(CreditCardId creditCardId);

    CreditCard byUserId(UserId userId);
}
