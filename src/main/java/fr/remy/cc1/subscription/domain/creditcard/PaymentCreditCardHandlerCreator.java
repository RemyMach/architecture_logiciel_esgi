package fr.remy.cc1.subscription.domain.creditcard;


import fr.remy.cc1.subscription.domain.PaymentCardMiddleware;

import java.util.List;

public final class PaymentCreditCardHandlerCreator {

    public static PaymentCardMiddleware buildPaymentHandlers(List<PaymentCardMiddleware> paymentCreditCardHandlers) {

        PaymentCardMiddleware firstPaymentCreditCardHandler = null;
        PaymentCardMiddleware previousPaymentCreditCardHandler = null;
        for(PaymentCardMiddleware paymentCreditCardHandler: paymentCreditCardHandlers) {
            if(firstPaymentCreditCardHandler != null && previousPaymentCreditCardHandler != null) {
                previousPaymentCreditCardHandler.setNext(paymentCreditCardHandler);
            }else {
                firstPaymentCreditCardHandler = paymentCreditCardHandler;
            }
            previousPaymentCreditCardHandler = paymentCreditCardHandler;
        }

        return firstPaymentCreditCardHandler;
    }
}
