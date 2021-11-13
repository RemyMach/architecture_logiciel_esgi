package fr.remy.cc1.domain.payment.creditcard;

import java.math.BigDecimal;

public abstract class PaymentCreditCardHandler {

    private PaymentCreditCardHandler nextHandler;

    public void setNext(PaymentCreditCardHandler handler) {
        this.nextHandler = handler;
    }

    public abstract void process(CreditCard creditCard, BigDecimal amount);

    public void checkNext(CreditCard creditCard, BigDecimal amount) {
        if(this.nextHandler != null) {
            this.nextHandler.process(creditCard, amount);
        }
    }
}
