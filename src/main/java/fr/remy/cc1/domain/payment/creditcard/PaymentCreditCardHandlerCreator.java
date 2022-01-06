package fr.remy.cc1.domain.payment.creditcard;


import fr.remy.cc1.domain.payment.PaymentCardMiddleware;

import java.util.List;

public class PaymentCreditCardHandlerCreator {

    public static PaymentCardMiddleware buildPaymentHandlers(List<PaymentCardMiddleware> paymentCreditCardHandlers) {

        PaymentCardMiddleware firstPaymentCreditCardHandler = null;
        PaymentCardMiddleware previousPaymentCreditCardHandler = null;
        for(PaymentCardMiddleware paymentCreditCardHandler: paymentCreditCardHandlers) {
            if(firstPaymentCreditCardHandler != null && previousPaymentCreditCardHandler != null) {
                previousPaymentCreditCardHandler.setNext(paymentCreditCardHandler);
                previousPaymentCreditCardHandler = paymentCreditCardHandler;
            }else {
                firstPaymentCreditCardHandler = paymentCreditCardHandler;
                previousPaymentCreditCardHandler = paymentCreditCardHandler;
            }
        }

        return firstPaymentCreditCardHandler;
    }
}
