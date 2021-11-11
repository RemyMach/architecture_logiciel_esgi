package fr.remy.cc1.domain.payment;

import fr.remy.cc1.domain.PaymentMethodValidator;
import fr.remy.cc1.infrastructure.payment.CreditCardPayment;
import fr.remy.cc1.infrastructure.payment.PaypalPayment;

import java.util.Arrays;
import java.util.List;

public class PaymentBuild {

    public static final List<String> PAYMENT_METHOD_SUPPORTED = Arrays.asList("Paypal", "CreditCard");

    private String exceptionMessage = "You can choose uniquely " + PAYMENT_METHOD_SUPPORTED.toString() + " to pay";

    public Payment getPaymentOf(String paymentMethod, Payor payor) {
        if(!PaymentMethodValidator.getInstance().test(paymentMethod)) {
            throw new IllegalArgumentException(exceptionMessage);
        }

        if(paymentMethod.equals(PAYMENT_METHOD_SUPPORTED.get(0))) {
            return new PaypalPayment();
        }else if(paymentMethod.equals(PAYMENT_METHOD_SUPPORTED.get(1))) {
            return new CreditCardPayment(payor.getCreditCard());
        }

        throw new IllegalArgumentException(exceptionMessage);
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

}
