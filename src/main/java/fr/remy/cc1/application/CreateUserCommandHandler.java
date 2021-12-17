package fr.remy.cc1.application;

import fr.remy.cc1.domain.user.*;
import fr.remy.cc1.kernel.CommandHandler;
import fr.remy.cc1.kernel.event.Event;
import fr.remy.cc1.kernel.event.EventBus;

public final class CreateUserCommandHandler implements CommandHandler<CreateUser, UserId> {

    private final Users users;
    private final UserService userService;
    private final EventBus<Event> eventBus;


    public CreateUserCommandHandler(Users users, UserService userService, EventBus<Event> eventBus) {
        this.users = users;
        this.userService = userService;
        this.eventBus = eventBus;
    }

    public UserId handle(CreateUser createUser) {
        final UserId userId = users.nextIdentity();
        User user = User.of(userId, createUser.lastname, createUser.firstname, createUser.email, createUser.password, createUser.userCategory);
        this.userService.create(user);
        this.eventBus.send(RegisterUserEvent.withUser(user));
        return userId;
    }
}
