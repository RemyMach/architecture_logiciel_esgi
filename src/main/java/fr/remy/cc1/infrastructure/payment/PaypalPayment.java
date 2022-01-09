package fr.remy.cc1.infrastructure.payment;

import fr.remy.cc1.domain.payment.Money;
import fr.remy.cc1.domain.payment.Payment;
import fr.remy.cc1.domain.payment.paypal.PaypalAccount;

public class PaypalPayment implements Payment {

    private final PaypalAccount paypalAccount;

    public PaypalPayment(PaypalAccount paypalAccount) {
        this.paypalAccount = paypalAccount;
    }
    @Override
    public void start(Money money) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }
}
