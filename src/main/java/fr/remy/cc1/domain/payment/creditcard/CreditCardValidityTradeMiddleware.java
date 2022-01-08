package fr.remy.cc1.domain.payment.creditcard;

import fr.remy.cc1.domain.payment.Money;
import fr.remy.cc1.domain.payment.PaymentCardMiddleware;
import fr.remy.cc1.kernel.error.PaymentProcessValidationException;

public class CreditCardValidityTradeMiddleware implements PaymentCardMiddleware {

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
