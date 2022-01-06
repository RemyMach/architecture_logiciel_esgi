package fr.remy.cc1.domain.payment;

import fr.remy.cc1.domain.payment.creditcard.CreditCard;
import fr.remy.cc1.domain.payment.paypal.PaypalAccount;
import fr.remy.cc1.infrastructure.payment.CreditCardPayment;
import fr.remy.cc1.infrastructure.payment.PaypalPayment;

public class PaymentDirector {

    //TODO replace by factory maybe
    public static Payment createCreditCardPayment(CreditCard creditCard, PaymentCardMiddleware paymentCreditCardHandler) {
        return new CreditCardPayment(creditCard, paymentCreditCardHandler);
    }

    public static Payment createPaypalPayment(PaypalAccount paypalAccount) {
        return new PaypalPayment(paypalAccount);
    }
}
