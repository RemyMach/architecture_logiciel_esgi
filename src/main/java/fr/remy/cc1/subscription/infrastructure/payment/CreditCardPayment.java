package fr.remy.cc1.subscription.infrastructure.payment;

import fr.remy.cc1.kernel.error.PaymentProcessValidationException;
import fr.remy.cc1.shared.domain.money.Money;
import fr.remy.cc1.subscription.domain.Payment;
import fr.remy.cc1.subscription.domain.PaymentCardMiddleware;
import fr.remy.cc1.subscription.domain.creditcard.CreditCard;

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
