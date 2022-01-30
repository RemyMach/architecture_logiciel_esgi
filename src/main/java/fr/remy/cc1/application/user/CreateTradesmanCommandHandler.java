package fr.remy.cc1.application.user;

import fr.remy.cc1.application.UserDTO;
import fr.remy.cc1.domain.user.*;
import fr.remy.cc1.kernel.CommandHandler;
import fr.remy.cc1.kernel.error.ValidationException;
import fr.remy.cc1.kernel.event.Event;
import fr.remy.cc1.kernel.event.EventBus;

public class CreateTradesmanCommandHandler implements CommandHandler<CreateTradesman, UserId> {

    private final Users users;
    private final EventBus<Event> eventBus;


    public CreateTradesmanCommandHandler(Users users, EventBus<Event> eventBus) {
        this.users = users;
        this.eventBus = eventBus;
    }

    @Override
    public UserId handle(CreateTradesman createTradesman) throws ValidationException {
        return null;
    }
}
