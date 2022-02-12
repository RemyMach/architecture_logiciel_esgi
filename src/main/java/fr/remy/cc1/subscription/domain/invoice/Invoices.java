package fr.remy.cc1.subscription.domain.invoice;

import fr.remy.cc1.subscription.domain.customer.SubscriptionOfferId;

import java.util.List;

public interface Invoices {
    void save(Invoice invoice);

    InvoiceId nextIdentity();

    List<Invoice> findAll();

    void saveSubscriptionInvoice(Invoice invoice, SubscriptionOfferId subscriptionOfferId);

    List<Invoice> findBySubscriptionOfferId(SubscriptionOfferId subscriptionOfferId);
}
