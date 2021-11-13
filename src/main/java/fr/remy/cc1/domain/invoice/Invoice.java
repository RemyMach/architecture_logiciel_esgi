package fr.remy.cc1.domain.invoice;

import fr.remy.cc1.domain.user.User;

import java.math.BigDecimal;

public final class Invoice {

    private final InvoiceId invoiceId;

    private final BigDecimal amount;

    private final User user;


    private Invoice(InvoiceId invoiceId,BigDecimal amount, User user) {
        this.invoiceId = invoiceId;
        this.amount = amount;
        this.user = user;
    }

    public static Invoice of(InvoiceId invoiceId, BigDecimal amount, User user) {
        return new Invoice(invoiceId , amount, user);
    }

    public InvoiceId getInvoiceId() {
        return invoiceId;
    }
}
