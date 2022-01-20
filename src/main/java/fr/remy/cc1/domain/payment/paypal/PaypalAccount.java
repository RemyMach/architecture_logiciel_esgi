package fr.remy.cc1.domain.payment.paypal;

import fr.remy.cc1.domain.user.UserId;

import java.util.Objects;

public final class PaypalAccount {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaypalAccount that = (PaypalAccount) o;
        return Objects.equals(payPalAccountId, that.payPalAccountId) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(payPalAccountId, userId);
    }
}
