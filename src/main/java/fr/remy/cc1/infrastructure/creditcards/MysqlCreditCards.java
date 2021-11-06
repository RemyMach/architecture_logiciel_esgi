package fr.remy.cc1.infrastructure.creditcards;

import fr.remy.cc1.domain.CreditCard;
import fr.remy.cc1.domain.CreditCards;
import fr.remy.cc1.domain.user.User;

public class MysqlCreditCards implements CreditCards {
    @Override
    public void save(CreditCard creditCard, User user) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }
}
