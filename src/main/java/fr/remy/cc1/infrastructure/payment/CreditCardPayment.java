package fr.remy.cc1.infrastructure.payment;

import fr.remy.cc1.domain.payment.creditcard.*;
import fr.remy.cc1.domain.payment.*;

import java.math.BigDecimal;
import java.util.List;

public class CreditCardPayment implements Payment {

    private final CreditCard creditCard;

    public CreditCardPayment(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    @Override
    public void start(BigDecimal amount) {

        PaymentCreditCardHandler paymentCreditCardHandler =
                PaymentCreditCardHandlerBuild.buildPaymentHandlers(
                        List.of(new CreditCardChecker(), new CreditCardApproveTradesman(), new CreditCardContractor())
                );
        paymentCreditCardHandler.process(this.creditCard, amount);
    }
}
