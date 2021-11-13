package fr.remy.cc1.domain.payment.creditcard;

import java.math.BigDecimal;

public class CreditCardChecker extends PaymentCreditCardHandler {

    @Override
    public void process(CreditCard creditCard, BigDecimal amount) {
        if(creditCard.getSecurityCode() == 420) {
            throw new IllegalArgumentException("The security code is not valid");
        }
        checkNext(creditCard, amount);
    }
}
