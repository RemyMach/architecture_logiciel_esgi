package fr.remy.cc1.domain.payment.creditcard;

import java.math.BigDecimal;

public class CreditCardApproveTradesman extends PaymentCreditCardHandler {

    @Override
    public void process(CreditCard creditCard, BigDecimal amount) {
        checkNext(creditCard, amount);
    }
}