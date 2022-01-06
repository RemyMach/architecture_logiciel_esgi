package fr.remy.cc1.domain.payment.creditcard;

import fr.remy.cc1.domain.payment.Money;

import java.math.BigDecimal;

public abstract class PaymentCreditCardHandler {

    private PaymentCreditCardHandler nextHandler;

    public void setNext(PaymentCreditCardHandler handler) {
        this.nextHandler = handler;
    }

    public abstract void process(CreditCard creditCard, Money money);

    public void checkNext(CreditCard creditCard, Money money) {
        if(this.nextHandler != null) {
            this.nextHandler.process(creditCard, money);
        }
    }
}
