package fr.remy.cc1.domain.payment;

import fr.remy.cc1.domain.payment.creditcard.CreditCard;
import fr.remy.cc1.domain.payment.creditcard.PaymentCreditCardHandler;
import fr.remy.cc1.domain.payment.paypal.PaypalAccount;

public interface PaymentBuilder {

    void withCreditCard(CreditCard creditCard);

    void withCreditCardHandler(PaymentCreditCardHandler paymentCreditCardHandler);

    void withPaypalAccount(PaypalAccount paypalAccount);
}
