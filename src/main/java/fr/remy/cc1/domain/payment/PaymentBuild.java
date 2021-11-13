package fr.remy.cc1.domain.payment;

import fr.remy.cc1.infrastructure.payment.CreditCardPayment;
import fr.remy.cc1.infrastructure.payment.PaypalPayment;

public class PaymentBuild {

    private String exceptionMessage = "You can choose uniquely " + Payer.PAYMENT_METHOD_SUPPORTED.toString() + " to pay";

    public Payment getPaymentOf(String paymentMethod, Payer payer) {
        if(!PaymentMethodValidator.getInstance().test(paymentMethod)) {
            throw new IllegalArgumentException(exceptionMessage);
        }

        if(paymentMethod.equals(Payer.PAYMENT_METHOD_SUPPORTED.get(0))) {
            return new PaypalPayment();
        }else if(paymentMethod.equals(Payer.PAYMENT_METHOD_SUPPORTED.get(1))) {
            return new CreditCardPayment(payer.getCreditCard());
        }

        throw new IllegalArgumentException(exceptionMessage);
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

}
