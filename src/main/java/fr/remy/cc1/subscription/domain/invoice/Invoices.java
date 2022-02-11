package fr.remy.cc1.subscription.domain.invoice;

import java.util.List;

public interface Invoices {
    void save(Invoice invoice);

    InvoiceId nextIdentity();

    List<Invoice> findAll();
}
