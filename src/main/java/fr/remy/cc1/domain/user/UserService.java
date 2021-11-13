package fr.remy.cc1.domain.user;

import fr.remy.cc1.infrastructure.user.UserCreationEventBus;

import java.util.List;

public final class UserService {

    private final Users users;

    public UserService(Users users) {
        this.users = users;
    }

    public void create(User user) {
        this.users.save(user);
        UserCreationEventBus.getInstance().send(RegisterUserEvent.withUser(user));
    }

    public List<User> all() {
        return this.users.findAll();
    }
}
