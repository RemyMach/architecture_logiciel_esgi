package fr.remy.cc1.certificate.domain.skill;

import fr.remy.cc1.subscription.domain.invoice.Invoice;
import fr.remy.cc1.subscription.domain.invoice.InvoiceId;

import java.util.List;

public interface Skills {
    void save(Skill skill);

    InvoiceId nextIdentity();

    List<Invoice> findAll();
}
