package fr.remy.cc1.subscription.domain;

import fr.remy.cc1.subscription.domain.creditcard.CreditCard;
import fr.remy.cc1.subscription.domain.paypal.PaypalAccount;
import fr.remy.cc1.subscription.infrastructure.payment.CreditCardPayment;
import fr.remy.cc1.subscription.infrastructure.payment.PaypalPayment;

public final class PaymentDirector {

    //TODO replace by factory maybe
    public static Payment createCreditCardPayment(CreditCard creditCard, PaymentCardMiddleware paymentCreditCardHandler) {
        return new CreditCardPayment(creditCard, paymentCreditCardHandler);
    }

    public static Payment createPaypalPayment(PaypalAccount paypalAccount) {
        return new PaypalPayment(paypalAccount);
    }
}
