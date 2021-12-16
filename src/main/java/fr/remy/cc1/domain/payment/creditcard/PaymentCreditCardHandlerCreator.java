package fr.remy.cc1.domain.payment.creditcard;


import java.util.List;

public class PaymentCreditCardHandlerCreator {

    public static PaymentCreditCardHandler buildPaymentHandlers(List<PaymentCreditCardHandler> paymentCreditCardHandlers) {

        PaymentCreditCardHandler firstPaymentCreditCardHandler = null;
        PaymentCreditCardHandler previousPaymentCreditCardHandler = null;
        for(PaymentCreditCardHandler paymentCreditCardHandler: paymentCreditCardHandlers) {
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
