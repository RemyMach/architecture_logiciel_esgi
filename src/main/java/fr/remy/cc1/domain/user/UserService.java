package fr.remy.cc1.domain.user;

import fr.remy.cc1.domain.PaymentService;
import fr.remy.cc1.domain.event.Event;
import fr.remy.cc1.domain.event.EventBus;
import fr.remy.cc1.infrastructure.RegisterUserEvent;

import java.util.List;

public final class UserService {

    private final Users users;

    private final EventBus<Event> eventBus;

    public UserService(Users users,EventBus eventBus) {
        this.users = users;
        this.eventBus = eventBus;
    }

    public void create(User user) {
        this.users.save(user);
        this.eventBus.send(RegisterUserEvent.withUser(user));
    }

    public List<User> all() {
        return this.users.findAll();
    }
}
