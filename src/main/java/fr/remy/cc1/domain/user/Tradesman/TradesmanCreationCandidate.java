package fr.remy.cc1.domain.user.Tradesman;

import fr.remy.cc1.domain.company.Company;
import fr.remy.cc1.domain.location.Country;
import fr.remy.cc1.domain.payment.Money;
import fr.remy.cc1.domain.skill.Skill;
import fr.remy.cc1.domain.trades.ETrade;
import fr.remy.cc1.domain.user.Email;
import fr.remy.cc1.domain.user.Password;
import fr.remy.cc1.domain.user.UserCandidate;
import fr.remy.cc1.domain.user.UserCategory;
import fr.remy.cc1.kernel.error.ValidationException;

import java.util.List;

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
