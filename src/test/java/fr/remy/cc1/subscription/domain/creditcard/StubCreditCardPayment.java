package fr.remy.cc1.subscription.domain.creditcard;

import fr.remy.cc1.subscription.domain.Money;
import fr.remy.cc1.subscription.domain.PaymentCardMiddleware;
import fr.remy.cc1.subscription.infrastructure.payment.CreditCardPayment;
import fr.remy.cc1.kernel.error.PaymentProcessValidationException;

public class StubCreditCardPayment extends CreditCardPayment {

    private CreditCard creditCard;
    private PaymentCardMiddleware paymentCreditCardHandler;

    public StubCreditCardPayment(
            CreditCard creditCard,
            PaymentCardMiddleware paymentCreditCardHandler
    ) {
        super(creditCard, paymentCreditCardHandler);
        this.creditCard = creditCard;
        this.paymentCreditCardHandler = paymentCreditCardHandler;
    }

    public void start(Money money) throws PaymentProcessValidationException {
        this.paymentCreditCardHandler.process(this.creditCard, money);
    }

}
