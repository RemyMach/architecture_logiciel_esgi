package fr.remy.cc1.infrastructure.invoices;

import fr.remy.cc1.domain.payment.Invoice;
import fr.remy.cc1.domain.payment.Invoices;

public class MysqlInvoices implements Invoices {
    @Override
    public void save(Invoice invoice) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }
}
