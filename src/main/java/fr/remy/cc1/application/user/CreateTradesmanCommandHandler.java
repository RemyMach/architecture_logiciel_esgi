package fr.remy.cc1.application.user;

import fr.remy.cc1.application.UserDTO;
import fr.remy.cc1.domain.user.*;
import fr.remy.cc1.domain.user.Tradesman.Tradesman;
import fr.remy.cc1.domain.user.Tradesman.TradesmanCreationCandidate;
import fr.remy.cc1.domain.user.Tradesman.Tradesmans;
import fr.remy.cc1.kernel.CommandHandler;
import fr.remy.cc1.kernel.error.ValidationException;
import fr.remy.cc1.kernel.event.Event;
import fr.remy.cc1.kernel.event.EventBus;

public class CreateTradesmanCommandHandler implements CommandHandler<CreateTradesman, UserId> {

    private final Users users;
    private final Tradesmans tradesmans;
    private final EventBus<Event> eventBus;

    public CreateTradesmanCommandHandler(Users users, Tradesmans tradesmans, EventBus<Event> eventBus) {
        this.users = users;
        this.eventBus = eventBus;
        this.tradesmans = tradesmans;
    }

    @Override
    public UserId handle(CreateTradesman createTradesman) throws ValidationException {
        TradesmanCreationCandidate tradesmanCreationCandidate = TradesmanCreationCandidate.of(createTradesman.lastname, createTradesman.firstname, new Email(createTradesman.email), new Password(createTradesman.password));
        final UserId userId = users.nextIdentity();
        Tradesman tradesman = Tradesman.of(userId, tradesmanCreationCandidate.lastname, tradesmanCreationCandidate.firstname, tradesmanCreationCandidate.email, tradesmanCreationCandidate.password, null, null, null, null);
        this.tradesmans.save(tradesman);
        this.users.save(User.of(tradesman.getUserId(), tradesman.getLastname(), tradesman.getFirstname(), tradesman.getEmail(), tradesman.getPassword(), UserCategory.TRADESMAN));
        this.eventBus.send(RegisterTradesmanEvent.withUser(new UserDTO(userId, tradesman.getLastname(), tradesman.getFirstname(), tradesman.getEmail())));
        return userId;
    }
}
