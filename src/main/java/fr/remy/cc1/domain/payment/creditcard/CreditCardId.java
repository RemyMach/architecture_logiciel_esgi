package fr.remy.cc1.domain.payment.creditcard;

import java.util.Objects;

public final class CreditCardId {

    private final int value;

    private CreditCardId(int value) {
        this.value = value;
    }

    public static CreditCardId of(int value) {
        return new CreditCardId(value);
    }

    public String getValue() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditCardId that = (CreditCardId) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
