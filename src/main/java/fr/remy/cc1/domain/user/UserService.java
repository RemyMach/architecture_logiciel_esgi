package fr.remy.cc1.domain.user;

import fr.remy.cc1.infrastructure.exceptions.NoSuchEntityException;
import fr.remy.cc1.infrastructure.user.UserCreationEventBus;

import java.util.List;

public final class UserService {

    private final Users users;

    public UserService(Users users) {
        this.users = users;
    }

    public void create(User user) {
        this.users.save(user);
    }

    public User getUser(UserId userId) throws NoSuchEntityException {
        return this.users.byId(userId);
    }

    public List<User> all() {
        return this.users.findAll();
    }
}
