package fr.remy.cc1.domain.payment.paypal;

import fr.remy.cc1.domain.user.UserId;

public class PaypalAccount {

    private final PayPalAccountId payPalAccountId;

    private final UserId userId;

    public PaypalAccount(PayPalAccountId payPalAccountId, UserId userId) {
        this.payPalAccountId = payPalAccountId;
        this.userId = userId;
    }

    public PayPalAccountId getPayPalAccountId() {
        return payPalAccountId;
    }

    public UserId getUserId() {
        return userId;
    }
}
