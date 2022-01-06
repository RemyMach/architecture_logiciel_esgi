package fr.remy.cc1.domain.invoice;

import fr.remy.cc1.domain.payment.Money;
import fr.remy.cc1.domain.user.User;
import fr.remy.cc1.domain.user.UserId;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public final class Invoice {

    private final InvoiceId invoiceId;

    private final Money money;

    private final ZonedDateTime createAt;

    private final UserId userId;


    private Invoice(InvoiceId invoiceId, Money money, UserId userId, ZonedDateTime createAt) {
        this.invoiceId = invoiceId;
        this.money = money;
        this.userId = userId;
        this.createAt = createAt;
    }

    public static Invoice of(InvoiceId invoiceId, Money money, UserId userId, ZonedDateTime createAt) {
        return new Invoice(invoiceId , money, userId, createAt);
    }

    public InvoiceId getInvoiceId() {
        return invoiceId;
    }
}
