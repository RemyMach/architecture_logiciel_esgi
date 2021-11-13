package fr.remy.cc1.domain.payment.creditcard;

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
}