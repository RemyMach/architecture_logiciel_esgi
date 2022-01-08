package fr.remy.cc1.exposition.authentication;

import fr.remy.cc1.domain.user.UserId;
import fr.remy.cc1.kernel.Command;

public class CreateAuthToken implements Command {
    public final UserId userId;

    public CreateAuthToken(UserId userId) {
        this.userId = userId;
    }
}
