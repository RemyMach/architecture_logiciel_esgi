package fr.remy.cc1.domain.payment;

import fr.remy.cc1.domain.CreditCard;

public class CreditCardContractor extends PaymentCreditCardHandler {

    @Override
    public boolean process(CreditCard creditCard) {
        System.out.println("je suis dans le contractor");
        return checkNext(creditCard);
    }
}
