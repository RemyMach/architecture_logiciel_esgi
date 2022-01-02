package fr.remy.cc1.application;

import fr.remy.cc1.domain.user.*;
import fr.remy.cc1.kernel.CommandHandler;
import fr.remy.cc1.kernel.error.BasicException;
import fr.remy.cc1.kernel.error.ValidationException;
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

    @Override
    public UserId handle(CreateUser createUser) throws ValidationException {
        final UserId userId = users.nextIdentity();
        User user = User.of(userId, createUser.lastname, createUser.firstname, createUser.email, createUser.password, createUser.userCategory);
        this.users.save(user);
        this.eventBus.send(RegisterUserEvent.withUser(user));
        return userId;
    }
}
