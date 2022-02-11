package fr.remy.cc1.member.domain.user.Tradesman;

import fr.remy.cc1.member.domain.user.Email;
import fr.remy.cc1.member.domain.user.Password;
import fr.remy.cc1.member.domain.user.UserCandidate;
import fr.remy.cc1.member.domain.user.UserCategory;
import fr.remy.cc1.kernel.error.ValidationException;

public class TradesmanCreationCandidate {

    public final String lastname;
    public final String firstname;
    public final Email email;
    public final Password password;

    private TradesmanCreationCandidate(String lastname, String firstname, Email email, Password password) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.password = password;
    }

    public static TradesmanCreationCandidate of(String lastname, String firstname, Email email, Password password) throws ValidationException {
        UserCandidate userCandidate = UserCandidate.of(lastname, firstname, email, password, UserCategory.TRADESMAN);
        return new TradesmanCreationCandidate( userCandidate.lastname, userCandidate.firstname, userCandidate.email,  userCandidate.password);
    }
}
