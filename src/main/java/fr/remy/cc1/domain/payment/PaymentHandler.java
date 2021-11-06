package fr.remy.cc1.domain.payment;

import fr.remy.cc1.domain.CreditCard;
import fr.remy.cc1.domain.Handler;

public abstract class PaymentHandler implements Handler {

    private Handler nextHandler;

    @Override
    public void setNext(Handler handler) {
        this.nextHandler = handler;
    }

    @Override
    public abstract boolean process(CreditCard creditCard);

    @Override
    public boolean checkNext() {
        if(this.nextHandler == null) {
            return false;
        }
        return this.nextHandler.process();
    }
}
