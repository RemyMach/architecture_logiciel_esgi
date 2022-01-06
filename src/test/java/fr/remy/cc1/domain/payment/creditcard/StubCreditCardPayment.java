package fr.remy.cc1.domain.payment.creditcard;

import fr.remy.cc1.domain.payment.Money;
import fr.remy.cc1.infrastructure.payment.CreditCardPayment;

import java.math.BigDecimal;
import java.util.List;

public class StubCreditCardPayment extends CreditCardPayment {

    private CreditCard creditCard;
    private PaymentCreditCardHandler paymentCreditCardHandler;

    public StubCreditCardPayment(
            CreditCard creditCard,
            PaymentCreditCardHandler paymentCreditCardHandler
    ) {
        super(creditCard, paymentCreditCardHandler);
        this.creditCard = creditCard;
        this.paymentCreditCardHandler = paymentCreditCardHandler;
    }

    public void start(Money money) {
        this.paymentCreditCardHandler.process(this.creditCard, money);
    }

}
