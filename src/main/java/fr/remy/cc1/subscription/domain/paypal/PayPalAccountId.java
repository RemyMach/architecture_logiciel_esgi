package fr.remy.cc1.subscription.domain.paypal;

import java.util.Objects;

public final class PayPalAccountId {
    private final int value;

    private PayPalAccountId(int value) {
        this.value = value;
    }

    public static PayPalAccountId of(int value) {
        return new PayPalAccountId(value);
    }

    public String getValue() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PayPalAccountId that = (PayPalAccountId) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
