package fr.remy.cc1.domain.payment;

public interface Invoices {
    void save(Invoice invoice);

    InvoiceId nextIdentity();
}
