package fr.remy.cc1.domain.payment;

import fr.remy.cc1.domain.payment.creditcard.CreditCard;
import fr.remy.cc1.kernel.error.PaymentProcessValidationException;

public interface PaymentCardMiddleware {
    
    public void setNext(PaymentCardMiddleware middleware);

    public void process(CreditCard creditCard, Money money) throws PaymentProcessValidationException;

    public void checkNext(CreditCard creditCard, Money money) throws PaymentProcessValidationException;
}
