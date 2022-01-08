package fr.remy.cc1.infrastructure.creditcards;

import fr.remy.cc1.domain.payment.creditcard.CreditCard;
import fr.remy.cc1.domain.payment.creditcard.CreditCards;
import fr.remy.cc1.domain.payment.creditcard.CreditCardId;
import fr.remy.cc1.domain.payment.paypal.PaypalAccount;
import fr.remy.cc1.domain.user.User;
import fr.remy.cc1.domain.user.UserId;

public class MysqlCreditCards implements CreditCards {
    @Override
    public void save(CreditCard creditCard, User user) {
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
