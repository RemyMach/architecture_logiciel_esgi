package fr.remy.cc1.domain.payment.creditcard;

import java.math.BigDecimal;

public class CreditCardChecker extends PaymentCreditCardHandler {

    @Override
    public void process(CreditCard creditCard, BigDecimal amount) {
        System.out.println("je passe bien par là");
        System.out.println(creditCard.getSecurityCode() == 420);
        if(creditCard.getSecurityCode() == 420) {
            throw new IllegalArgumentException("The security code is not valid");
        }
        checkNext(creditCard, amount);
    }
}
