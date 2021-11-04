package fr.remy.cc1.infrastructure.users;

import fr.remy.cc1.domain.User;
import fr.remy.cc1.domain.UserId;
import fr.remy.cc1.domain.Users;

import java.util.List;

public class MysqlUsers implements Users {
    @Override
    public void save(User user) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    @Override
    public User byId(UserId userId) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    @Override
    public UserId nextIdentity() {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    @Override
    public List<User> findAll() {
        throw new UnsupportedOperationException("Not yet implemented.");
    }
}
