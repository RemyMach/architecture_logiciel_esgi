package fr.remy.cc1.shared.exposition.authentication;

import fr.remy.cc1.kernel.Command;
import fr.remy.cc1.member.domain.user.Email;
import fr.remy.cc1.shared.domain.UserId;

public class CreateAuthToken implements Command {
    public final UserId userId;
    public final Email email;

    public CreateAuthToken(UserId userId, Email email) {
        this.userId = userId;
        this.email = email;
    }
}
