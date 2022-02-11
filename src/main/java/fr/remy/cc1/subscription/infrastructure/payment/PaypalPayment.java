package fr.remy.cc1.subscription.infrastructure.payment;

import fr.remy.cc1.subscription.domain.Money;
import fr.remy.cc1.subscription.domain.Payment;
import fr.remy.cc1.subscription.domain.paypal.PaypalAccount;

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
