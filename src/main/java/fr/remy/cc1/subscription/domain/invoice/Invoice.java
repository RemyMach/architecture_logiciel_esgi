package fr.remy.cc1.subscription.domain.invoice;

import fr.remy.cc1.domain.UserId;
import fr.remy.cc1.subscription.domain.Money;
import fr.remy.cc1.subscription.domain.PaymentState;

import java.time.ZonedDateTime;
import java.util.Objects;

public final class Invoice {

    private final InvoiceId invoiceId;

    private final Money money;

    private final ZonedDateTime createAt;

    private final PaymentState paymentState;

    private final UserId userId;


    private Invoice(InvoiceId invoiceId, Money money, UserId userId, PaymentState paymentState, ZonedDateTime createAt) {
        this.invoiceId = invoiceId;
        this.money = money;
        this.paymentState = paymentState;
        this.userId = userId;
        this.createAt = createAt;
    }

    public static Invoice of(InvoiceId invoiceId, Money money, UserId userId, PaymentState paymentState, ZonedDateTime createAt) {
        return new Invoice(invoiceId , money, userId, paymentState, createAt);
    }

    public InvoiceId getInvoiceId() {
        return invoiceId;
    }

    public ZonedDateTime getCreateAt() {
        return createAt;
    }

    public PaymentState getPaymentState() {
        return paymentState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return Objects.equals(invoiceId, invoice.invoiceId) && Objects.equals(money, invoice.money) && Objects.equals(createAt, invoice.createAt) && paymentState == invoice.paymentState && Objects.equals(userId, invoice.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoiceId, money, createAt, paymentState, userId);
    }
}
