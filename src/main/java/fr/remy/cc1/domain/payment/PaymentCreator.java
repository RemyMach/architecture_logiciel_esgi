package fr.remy.cc1.domain.payment;

import fr.remy.cc1.domain.payment.creditcard.CreditCard;
import fr.remy.cc1.domain.payment.creditcard.PaymentCreditCardHandler;
import fr.remy.cc1.domain.payment.paypal.PaypalAccount;
import fr.remy.cc1.infrastructure.payment.CreditCardPayment;
import fr.remy.cc1.infrastructure.payment.PaypalPayment;

public class PaymentCreator {

    public static Payment withCreditCard(CreditCard creditCard, PaymentCreditCardHandler paymentCreditCardHandler) {
        return new CreditCardPayment(creditCard, paymentCreditCardHandler);
    }

    public static Payment withPaypal(PaypalAccount paypalAccount) {
        return new PaypalPayment(paypalAccount);
    }
}
