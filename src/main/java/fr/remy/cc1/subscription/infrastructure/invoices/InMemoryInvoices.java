package fr.remy.cc1.subscription.infrastructure.invoices;

import fr.remy.cc1.subscription.domain.customer.SubscriptionOfferId;
import fr.remy.cc1.subscription.domain.invoice.Invoice;
import fr.remy.cc1.subscription.domain.invoice.InvoiceId;
import fr.remy.cc1.subscription.domain.invoice.Invoices;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class InMemoryInvoices implements Invoices {

    private final AtomicInteger counter = new AtomicInteger(0);
    private final Map<InvoiceId, Invoice> data = new ConcurrentHashMap<>();
    private final Map<SubscriptionOfferId, List<Invoice>> subscriptionInvoices;

    public InMemoryInvoices(Map<SubscriptionOfferId, List<Invoice>> subscriptionInvoices) {
        this.subscriptionInvoices = subscriptionInvoices;
    }

    @Override
    public void save(Invoice invoice) {
        data.put(invoice.getInvoiceId(), invoice);
    }

    public void saveSubscriptionInvoice(Invoice invoice, SubscriptionOfferId subscriptionOfferId) {
        this.save(invoice);
        if(findBySubscriptionOfferId(subscriptionOfferId) == null) {
            subscriptionInvoices.put(subscriptionOfferId, List.of(invoice));
        }else {
            List<Invoice> invoiceListCopy = new ArrayList<>(List.copyOf(findBySubscriptionOfferId(subscriptionOfferId)));
            invoiceListCopy.add(invoice);
            subscriptionInvoices.put(subscriptionOfferId, invoiceListCopy);
        }
    }

    @Override
    public InvoiceId nextIdentity()  {
        return InvoiceId.of(counter.incrementAndGet());
    }

    @Override
    public List<Invoice> findAll() {
        return data.values().stream().collect(Collectors.toList());
    }

    @Override
    public List<Invoice> findBySubscriptionOfferId(SubscriptionOfferId subscriptionOfferId) {
        return subscriptionInvoices.get(subscriptionOfferId);
    }
}
