package fr.remy.cc1.domain.skill;

import fr.remy.cc1.domain.invoice.Invoice;
import fr.remy.cc1.domain.invoice.InvoiceId;

import java.util.List;

public interface Skills {
    void save(Skill skill);

    InvoiceId nextIdentity();

    List<Invoice> findAll();
}
