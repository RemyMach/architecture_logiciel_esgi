package fr.remy.cc1.exposition.authentication;

import fr.remy.cc1.member.domain.user.Email;
import fr.remy.cc1.domain.UserId;
import fr.remy.cc1.kernel.Command;

public class CreateAuthToken implements Command {
    public final UserId userId;
    public final Email email;

    public CreateAuthToken(UserId userId, Email email) {
        this.userId = userId;
        this.email = email;
    }
}
