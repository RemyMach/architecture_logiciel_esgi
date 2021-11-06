package fr.remy.cc1.domain.payment;

import fr.remy.cc1.domain.CreditCard;

public class CreditCardApproveTradesman extends PaymentCreditCardHandler {

    @Override
    public boolean process(CreditCard creditCard) {
        System.out.println("je suis dans le approve tradesman");
        return checkNext(creditCard);
    }
}
