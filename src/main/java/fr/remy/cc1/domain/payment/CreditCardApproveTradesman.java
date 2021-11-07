package fr.remy.cc1.domain.payment;

import fr.remy.cc1.domain.CreditCard;

public class CreditCardApproveTradesman extends PaymentCreditCardHandler {

    @Override
    public void process(CreditCard creditCard) {
        System.out.println("je suis dans le approve tradesman");
        checkNext(creditCard);
    }
}
