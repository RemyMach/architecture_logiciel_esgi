package fr.remy.cc1.domain.user.Tradesman;

import fr.remy.cc1.domain.user.contractor.Contractor;

import java.util.List;

public interface Tradesmans {
    void save(Tradesman contractor);

    List<Tradesman> findAll();
}
