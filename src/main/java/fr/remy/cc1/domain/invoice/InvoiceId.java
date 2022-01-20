package fr.remy.cc1.domain.invoice;

import java.util.Objects;

public final class InvoiceId {

    private final int value;

    private InvoiceId(int value) {
        this.value = value;
    }

    public static InvoiceId of(int value) {
        return new InvoiceId(value);
    }

    public String getValue() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceId invoiceId = (InvoiceId) o;
        return value == invoiceId.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
