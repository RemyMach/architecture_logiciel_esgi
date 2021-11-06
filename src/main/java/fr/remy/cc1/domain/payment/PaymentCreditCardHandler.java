package fr.remy.cc1.domain.payment;

import fr.remy.cc1.domain.CreditCard;

public abstract class PaymentCreditCardHandler {

    private PaymentCreditCardHandler nextHandler;

    public void setNext(PaymentCreditCardHandler handler) {
        this.nextHandler = handler;
    }

    public abstract boolean process(CreditCard creditCard);

    public boolean checkNext(CreditCard creditCard) {
        if(this.nextHandler == null) {
            return false;
        }
        return this.nextHandler.process(creditCard);
    }
}
