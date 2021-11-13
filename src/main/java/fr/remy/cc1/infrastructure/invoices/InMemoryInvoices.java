package fr.remy.cc1.infrastructure.invoices;

import fr.remy.cc1.domain.invoice.Invoice;
import fr.remy.cc1.domain.invoice.InvoiceId;
import fr.remy.cc1.domain.invoice.Invoices;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

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

    @Override
    public List<Invoice> findAll() {
        return data.values().stream().collect(Collectors.toList());
    }
}
