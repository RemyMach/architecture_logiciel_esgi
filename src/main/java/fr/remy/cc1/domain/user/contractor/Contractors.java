package fr.remy.cc1.domain.user.contractor;

import java.util.List;

public interface Contractors {

    void save(Contractor contractor);

    List<Contractor> findAll();
}
