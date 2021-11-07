package fr.remy.cc1.domain.payment;

import fr.remy.cc1.domain.CreditCard;

public abstract class PaymentCreditCardHandler {

    private PaymentCreditCardHandler nextHandler;

    public void setNext(PaymentCreditCardHandler handler) {
        this.nextHandler = handler;
    }

    public abstract void process(CreditCard creditCard);

    public void checkNext(CreditCard creditCard) {
        if(this.nextHandler != null) {
            this.nextHandler.process(creditCard);
        }
    }
}
