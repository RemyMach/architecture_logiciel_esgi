package fr.remy.cc1.subscription.domain.creditcard;

import fr.remy.cc1.kernel.error.PaymentProcessValidationException;
import fr.remy.cc1.domain.money.Money;
import fr.remy.cc1.subscription.domain.PaymentCardMiddleware;

public class CreditCardBankAccountValidityMiddleware implements PaymentCardMiddleware {

    private PaymentCardMiddleware nextMiddleware;

    @Override
    public void process(CreditCard creditCard, Money money) throws PaymentProcessValidationException {
        checkNext(creditCard, money);
    }

    @Override
    public void setNext(PaymentCardMiddleware middleware) {
        this.nextMiddleware = middleware;
    }

    @Override
    public void checkNext(CreditCard creditCard, Money money) throws PaymentProcessValidationException {
        if(this.nextMiddleware != null) {
            this.nextMiddleware.process(creditCard, money);
        }
    }
}
