package fr.remy.cc1.subscription.infrastructure.creditcards;

import fr.remy.cc1.domain.UserId;
import fr.remy.cc1.subscription.domain.creditcard.CreditCard;
import fr.remy.cc1.subscription.domain.creditcard.CreditCardId;
import fr.remy.cc1.subscription.domain.creditcard.CreditCards;

public class MysqlCreditCards implements CreditCards {
    @Override
    public void save(CreditCard creditCard, UserId userId) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    @Override
    public CreditCardId nextIdentity() {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    @Override
    public CreditCard byId(CreditCardId creditCardId) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    @Override
    public CreditCard findByUserId(UserId userId) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    @Override
    public void delete(CreditCardId creditCardId) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }
}
