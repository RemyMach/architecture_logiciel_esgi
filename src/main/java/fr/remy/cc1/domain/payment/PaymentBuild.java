package fr.remy.cc1.domain.payment;

import fr.remy.cc1.domain.payment.creditcard.*;
import fr.remy.cc1.infrastructure.payment.CreditCardPayment;
import fr.remy.cc1.infrastructure.payment.PaypalPayment;

import java.util.List;

public class PaymentBuild {

    public Payment getPaymentOf(String paymentMethod, Payer payer) {
        if(!PaymentMethodValidator.getInstance().test(paymentMethod)) {
            throw new IllegalArgumentException(PaymentMethodValidator.exceptionMessage);
        }

        if(paymentMethod.equals(Payer.PAYMENT_METHOD_SUPPORTED.get(0))) {
            return new PaypalPayment(payer.getPaypalAccount());
        }else if(paymentMethod.equals(Payer.PAYMENT_METHOD_SUPPORTED.get(1))) {
            PaymentCreditCardHandler paymentCreditCardHandler =
                    PaymentCreditCardHandlerBuild.buildPaymentHandlers(
                            List.of(new CreditCardChecker(), new CreditCardApproveTradesman(), new CreditCardContractor())
                    );
            return new CreditCardPayment(payer.getCreditCard(), paymentCreditCardHandler);
        }

        throw new IllegalArgumentException(PaymentMethodValidator.exceptionMessage);
    }
}
