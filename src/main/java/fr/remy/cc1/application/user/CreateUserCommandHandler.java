package fr.remy.cc1.application.user;

import fr.remy.cc1.application.UserDTO;
import fr.remy.cc1.domain.user.*;
import fr.remy.cc1.kernel.CommandHandler;
import fr.remy.cc1.kernel.error.ValidationException;
import fr.remy.cc1.kernel.event.Event;
import fr.remy.cc1.kernel.event.EventBus;

public final class CreateUserCommandHandler implements CommandHandler<CreateUser, UserId> {

    private final Users users;
    private final EventBus<Event> eventBus;


    public CreateUserCommandHandler(Users users, EventBus<Event> eventBus) {
        this.users = users;
        this.eventBus = eventBus;
    }

    @Override
    public UserId handle(CreateUser createUser) throws ValidationException {
        UserCandidate userCandidate = UserCandidate.of(createUser.lastname, createUser.firstname, new Email(createUser.email), new Password(createUser.password), createUser.userCategory);
        final UserId userId = users.nextIdentity();
        User user = User.of(userId, userCandidate.lastname, userCandidate.firstname, userCandidate.email, userCandidate.password, userCandidate.userCategory);
        this.users.save(user);
        this.eventBus.send(RegisteredUserEvent.withUser(new UserDTO(userId, createUser.lastname, createUser.firstname, new Email(createUser.email))));
        return userId;
    }
}
