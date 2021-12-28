package fr.remy.cc1.application;

import fr.remy.cc1.domain.customer.SubscriptionOffer;
import fr.remy.cc1.domain.user.UserId;
import fr.remy.cc1.domain.user.UserService;
import fr.remy.cc1.domain.user.Users;
import fr.remy.cc1.kernel.CommandHandler;
import fr.remy.cc1.kernel.error.ValidationException;
import fr.remy.cc1.kernel.event.Event;
import fr.remy.cc1.kernel.event.EventBus;

public class CreateSubscriptionOfferCommandHandler implements CommandHandler<CreateSubscriptionOffer, Void> {

    private final Users users;
    private final UserService userService;
    private final EventBus<Event> eventBus;

    public CreateSubscriptionOfferCommandHandler(Users users, UserService userService, EventBus<Event> eventBus) {
        this.users = users;
        this.userService = userService;
        this.eventBus = eventBus;
    }

    @Override
    public Void handle(CreateSubscriptionOffer command) throws ValidationException {
        return null;
    }
}
