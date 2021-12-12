package fr.remy.cc1.domain.payment.paypal;

import fr.remy.cc1.domain.payment.PaymentBuilder;
import fr.remy.cc1.domain.payment.creditcard.CreditCard;
import fr.remy.cc1.domain.payment.creditcard.PaymentCreditCardHandler;
import fr.remy.cc1.infrastructure.payment.CreditCardPayment;
import fr.remy.cc1.infrastructure.payment.PaypalPayment;

public class PaypalPaymentBuilder implements PaymentBuilder {

    private PaypalAccount paypalAccount;

    @Override
    public void withCreditCard(CreditCard creditCard) {
        throw new Error("You don't have to use this construction for this object");
    }

    @Override
    public void withCreditCardHandler(PaymentCreditCardHandler paymentCreditCardHandler) {
        throw new Error("You don't have to use this construction for this object");
    }

    @Override
    public void withPaypalAccount(PaypalAccount paypalAccount) {
        this.paypalAccount = paypalAccount;
    }

    public PaypalPayment getPaypalPayment() {
        return new PaypalPayment(this.paypalAccount);
    }
}
