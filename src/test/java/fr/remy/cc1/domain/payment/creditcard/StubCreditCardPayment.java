package fr.remy.cc1.domain.payment.creditcard;

import fr.remy.cc1.domain.payment.Money;
import fr.remy.cc1.domain.payment.PaymentCardMiddleware;
import fr.remy.cc1.infrastructure.payment.CreditCardPayment;

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

    public void start(Money money) {
        this.paymentCreditCardHandler.process(this.creditCard, money);
    }

}
