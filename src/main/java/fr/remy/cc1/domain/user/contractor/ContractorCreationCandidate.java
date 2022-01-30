package fr.remy.cc1.domain.user.contractor;

import fr.remy.cc1.domain.company.Company;
import fr.remy.cc1.domain.user.Email;
import fr.remy.cc1.domain.user.Password;
import fr.remy.cc1.domain.user.UserCandidate;
import fr.remy.cc1.domain.user.UserCategory;
import fr.remy.cc1.kernel.error.ValidationException;

public class ContractorCreationCandidate {
    public final String lastname;
    public final String firstname;
    public final Email email;
    public final Password password;
    public final Company company;

    private ContractorCreationCandidate(String lastname, String firstname, Email email, Password password, Company company) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.password = password;
        this.company = company;
    }

    public static ContractorCreationCandidate of(String lastname, String firstname, Email email, Password password, Company company) throws ValidationException {
        UserCandidate userCandidate = UserCandidate.of(lastname, firstname, email, password, UserCategory.TRADESMAN);
        return new ContractorCreationCandidate( userCandidate.lastname, userCandidate.firstname, userCandidate.email,  userCandidate.password, company);
    }
}
