package fr.remy.cc1.infrastructure.invoices;

import fr.remy.cc1.domain.payment.CreditCardId;
import fr.remy.cc1.domain.payment.Invoice;
import fr.remy.cc1.domain.payment.InvoiceId;
import fr.remy.cc1.domain.payment.Invoices;

public class MysqlInvoices implements Invoices {
    @Override
    public void save(Invoice invoice) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    @Override
    public InvoiceId nextIdentity() {
        throw new UnsupportedOperationException("Not yet implemented.");
    }
}
