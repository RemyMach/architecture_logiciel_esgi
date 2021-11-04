package fr.remy.cc1.infrastructure.payment;

import fr.remy.cc1.domain.CreditCard;
import fr.remy.cc1.domain.payment.Payment;


public class CreditCardPayment implements Payment {

    private final CreditCard creditCard;

    public CreditCardPayment(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    @Override
    public boolean process(int amount) {
        return false;
    }
}
