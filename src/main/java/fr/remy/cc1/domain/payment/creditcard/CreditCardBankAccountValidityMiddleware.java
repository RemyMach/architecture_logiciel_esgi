package fr.remy.cc1.domain.payment.creditcard;

import fr.remy.cc1.domain.payment.Money;
import fr.remy.cc1.domain.payment.PaymentCardMiddleware;

public class CreditCardBankAccountValidityMiddleware implements PaymentCardMiddleware {

    private PaymentCardMiddleware nextMiddleware;

    @Override
    public void process(CreditCard creditCard, Money money) {
        checkNext(creditCard, money);
    }

    @Override
    public void setNext(PaymentCardMiddleware middleware) {
        this.nextMiddleware = middleware;
    }

    @Override
    public void checkNext(CreditCard creditCard, Money money) {
        if(this.nextMiddleware != null) {
            this.nextMiddleware.process(creditCard, money);
        }
    }
}
