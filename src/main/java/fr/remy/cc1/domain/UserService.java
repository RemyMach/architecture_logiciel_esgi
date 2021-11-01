package fr.remy.cc1.domain;

import fr.remy.cc1.domain.event.Event;
import fr.remy.cc1.domain.event.EventBus;
import fr.remy.cc1.infrastructure.RegisterUserEvent;

import java.util.List;

public final class UserService {

    private final Users users;

    private final EventBus<Event> eventBus;

    private final Handler paymentHandler;

    public UserService(Users users,EventBus eventBus, Handler paymentHandler) {
        this.users = users;
        this.eventBus = eventBus;
        this.paymentHandler = paymentHandler;
    }

    public void create(User user) {
        this.paymentHandler.process();
        this.users.save(user);
        this.eventBus.send(RegisterUserEvent.withUser(user));
    }

    public List<User> all() {
        return this.users.findAll();
    }
}
