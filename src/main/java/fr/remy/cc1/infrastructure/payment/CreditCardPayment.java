package fr.remy.cc1.infrastructure.payment;

import fr.remy.cc1.domain.CreditCard;
import fr.remy.cc1.domain.Handler;
import fr.remy.cc1.domain.payment.ApproveTradesman;
import fr.remy.cc1.domain.payment.Contractor;
import fr.remy.cc1.domain.payment.Payment;
import fr.remy.cc1.domain.payment.PaymentProcess;

import java.math.BigDecimal;


public class CreditCardPayment implements Payment {

    private final CreditCard creditCard;

    public CreditCardPayment(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    @Override
    public boolean start(BigDecimal amount) {

        Handler approveTradesman = new ApproveTradesman();
        Handler contractor = new Contractor();
        Handler paymentProcess = new PaymentProcess();
        paymentProcess.setNext(approveTradesman);
        approveTradesman.setNext(contractor);

        paymentProcess.process();
        return true;
    }
}
