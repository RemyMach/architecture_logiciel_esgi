package fr.remy.cc1.infrastructure.invoices;

import fr.remy.cc1.domain.CreditCard;
import fr.remy.cc1.domain.payment.CreditCardId;
import fr.remy.cc1.domain.payment.Invoice;
import fr.remy.cc1.domain.payment.InvoiceId;
import fr.remy.cc1.domain.payment.Invoices;
import fr.remy.cc1.domain.user.UserId;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryInvoices implements Invoices {

    private final AtomicInteger counter = new AtomicInteger(0);
    private final Map<InvoiceId, Invoice> data = new ConcurrentHashMap<>();

    @Override
    public void save(Invoice invoice) {
        data.put(invoice.getInvoiceId(), invoice);
    }

    @Override
    public InvoiceId nextIdentity()  {
        return InvoiceId.of(counter.incrementAndGet());
    }
}
