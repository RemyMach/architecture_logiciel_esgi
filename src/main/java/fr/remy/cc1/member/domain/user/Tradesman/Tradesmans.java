package fr.remy.cc1.member.domain.user.Tradesman;

import java.util.List;

public interface Tradesmans {
    void save(Tradesman contractor);

    List<Tradesman> findAll();
}
