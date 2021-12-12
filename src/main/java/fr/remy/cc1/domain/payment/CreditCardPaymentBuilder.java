package fr.remy.cc1.domain.payment;

import fr.remy.cc1.domain.payment.creditcard.CreditCard;
import fr.remy.cc1.domain.payment.creditcard.PaymentCreditCardHandler;
import fr.remy.cc1.domain.payment.paypal.PaypalAccount;
import fr.remy.cc1.infrastructure.payment.CreditCardPayment;

public class CreditCardPaymentBuilder implements PaymentBuilder{

    private CreditCard creditCard;
    private PaymentCreditCardHandler paymentCreditCardHandler;

    @Override
    public void withCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    @Override
    public void withCreditCardHandler(PaymentCreditCardHandler paymentCreditCardHandler) {
        this.paymentCreditCardHandler = paymentCreditCardHandler;
    }

    @Override
    public void withPaypalAccount(PaypalAccount paypalAccount) {
        throw new Error("You don't have to use this construction for this object");
    }

    public CreditCardPayment getCreditCardPayment() {
        return new CreditCardPayment(this.creditCard, this.paymentCreditCardHandler);
    }
}
