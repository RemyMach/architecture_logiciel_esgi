package fr.remy.cc1.subscription.domain;

import fr.remy.cc1.domain.money.Money;
import fr.remy.cc1.kernel.error.PaymentProcessValidationException;
import fr.remy.cc1.subscription.domain.creditcard.CreditCard;

public interface PaymentCardMiddleware {
    
    public void setNext(PaymentCardMiddleware middleware);

    public void process(CreditCard creditCard, Money money) throws PaymentProcessValidationException;

    public void checkNext(CreditCard creditCard, Money money) throws PaymentProcessValidationException;
}
