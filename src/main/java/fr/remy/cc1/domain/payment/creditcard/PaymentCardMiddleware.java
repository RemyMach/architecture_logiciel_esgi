package fr.remy.cc1.domain.payment.creditcard;

import fr.remy.cc1.domain.payment.Money;

public interface PaymentCardMiddleware {
    
    public void setNext(PaymentCardMiddleware middleware);

    public void process(CreditCard creditCard, Money money);

    public void checkNext(CreditCard creditCard, Money money);
}
