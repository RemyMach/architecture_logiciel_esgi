package fr.remy.cc1.domain.user;

import java.util.List;

public interface Contractors {

    UserId nextIdentity();

    List<User> findAll();
}
