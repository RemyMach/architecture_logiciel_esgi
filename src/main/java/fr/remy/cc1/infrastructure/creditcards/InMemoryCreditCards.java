package fr.remy.cc1.infrastructure.creditcards;
import fr.remy.cc1.domain.CreditCard;
import fr.remy.cc1.domain.CreditCards;
import fr.remy.cc1.domain.user.User;

public class InMemoryCreditCards implements CreditCards {
    @Override
    public void save(CreditCard creditCard, User user) {
        System.out.println(" on save une credit card");
    }
}
