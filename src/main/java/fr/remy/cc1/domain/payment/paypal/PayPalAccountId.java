package fr.remy.cc1.domain.payment.paypal;

public class PayPalAccountId {
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
}
