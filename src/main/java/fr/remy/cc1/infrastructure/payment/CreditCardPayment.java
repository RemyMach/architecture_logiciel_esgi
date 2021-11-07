package fr.remy.cc1.infrastructure.payment;

import fr.remy.cc1.domain.CreditCard;
import fr.remy.cc1.domain.payment.*;
import java.math.BigDecimal;

public class CreditCardPayment implements Payment {

    private final CreditCard creditCard;

    public CreditCardPayment(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    @Override
    public void start(BigDecimal amount) {

        PaymentCreditCardHandler firstPaymentHandler = this.buildPaymentHandlers();
        firstPaymentHandler.process(this.creditCard);
    }

    private PaymentCreditCardHandler buildPaymentHandlers() {

        PaymentCreditCardHandler paymentProcess = new CreditCardChecker();
        PaymentCreditCardHandler approveTradesman = new CreditCardApproveTradesman();
        PaymentCreditCardHandler contractor = new CreditCardContractor();

        paymentProcess.setNext(approveTradesman);
        approveTradesman.setNext(contractor);

        return paymentProcess;
    }
}
