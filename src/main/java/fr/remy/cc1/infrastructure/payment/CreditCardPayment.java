package fr.remy.cc1.infrastructure.payment;

import fr.remy.cc1.domain.payment.creditcard.*;
import fr.remy.cc1.domain.payment.*;

import java.math.BigDecimal;
import java.util.List;

public class CreditCardPayment implements Payment {

    private final CreditCard creditCard;

    private final PaymentCreditCardHandler paymentCreditCardHandler;

    public CreditCardPayment(CreditCard creditCard, PaymentCreditCardHandler paymentCreditCardHandler ) {
        this.creditCard = creditCard;
        this.paymentCreditCardHandler = paymentCreditCardHandler;
    }

    @Override
    public void start(BigDecimal amount) {
        this.paymentCreditCardHandler.process(this.creditCard, amount);
    }
}
