package fr.remy.cc1.infrastructure.invoices;

import fr.remy.cc1.domain.payment.CreditCardId;
import fr.remy.cc1.domain.payment.Invoice;
import fr.remy.cc1.domain.payment.InvoiceId;
import fr.remy.cc1.domain.payment.Invoices;
import fr.remy.cc1.domain.user.User;

import java.util.List;
import java.util.stream.Collectors;

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
}
