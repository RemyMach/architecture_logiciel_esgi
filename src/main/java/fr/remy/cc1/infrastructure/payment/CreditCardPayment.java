package fr.remy.cc1.infrastructure.payment;

import fr.remy.cc1.domain.payment.Money;
import fr.remy.cc1.domain.payment.Payment;
import fr.remy.cc1.domain.payment.PaymentCardMiddleware;
import fr.remy.cc1.domain.payment.creditcard.CreditCard;
import fr.remy.cc1.kernel.error.PaymentProcessValidationException;

public class CreditCardPayment implements Payment {

    private final CreditCard creditCard;

    private final PaymentCardMiddleware paymentCreditCardHandler;

    public CreditCardPayment(CreditCard creditCard, PaymentCardMiddleware paymentCreditCardHandler ) {
        this.creditCard = creditCard;
        this.paymentCreditCardHandler = paymentCreditCardHandler;
    }

    @Override
    public void start(Money money) throws PaymentProcessValidationException {
        this.paymentCreditCardHandler.process(this.creditCard, money);
    }
}
