package fr.remy.cc1.subscription.infrastructure.invoices;

import fr.remy.cc1.subscription.domain.customer.SubscriptionOfferId;
import fr.remy.cc1.subscription.domain.invoice.Invoice;
import fr.remy.cc1.subscription.domain.invoice.InvoiceId;
import fr.remy.cc1.subscription.domain.invoice.Invoices;

import java.util.List;

public class MysqlInvoices implements Invoices {
    @Override
    public void save(Invoice invoice) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    @Override
    public InvoiceId nextIdentity() {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    @Override
    public List<Invoice> findAll() {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    @Override
    public void saveSubscriptionInvoice(Invoice invoice, SubscriptionOfferId subscriptionOfferId) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    @Override
    public List<Invoice> findBySubscriptionOfferId(SubscriptionOfferId subscriptionOfferId) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }
}
