package fr.remy.cc1.domain.payment.creditcard;

import fr.remy.cc1.domain.payment.Money;

import java.math.BigDecimal;

public class CreditCardContractor extends PaymentCreditCardHandler {

    @Override
    public void process(CreditCard creditCard, Money money) {
        checkNext(creditCard, money);
    }
}
