package fr.remy.cc1.domain;
import java.util.List;

public interface Users {
    void save(User user);

    User byId(UserId userId);

    UserId nextIdentity();

    List<User> findAll();
}
