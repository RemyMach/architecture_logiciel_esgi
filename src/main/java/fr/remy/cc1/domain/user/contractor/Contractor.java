package fr.remy.cc1.domain.user.contractor;

import fr.remy.cc1.domain.company.Company;
import fr.remy.cc1.domain.user.*;
import fr.remy.cc1.kernel.error.ValidationException;

public final class Contractor implements IUser {

    private final UserId userId;
    private final String lastname;
    private final String firstname;
    private final Email email;
    private final Password password;
    private final Company company;


    private Contractor(UserId userId, String lastname, String firstname, Email email, Password password, Company company) {
        this.userId = userId;
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.password = password;
        this.company = company;
    }

    public static Contractor of(UserId userId, String lastname, String firstname, Email email, Password password, Company company) throws ValidationException {
        UserCandidate userCandidate = UserCandidate.of(lastname, firstname, email, password, UserCategory.TRADESMAN);
        return new Contractor(userId, userCandidate.lastname, userCandidate.firstname, userCandidate.email,  userCandidate.password, company);
    }

    @Override
    public UserId getUserId() {
        return userId;
    }

    public Company getCompany() {
        return company;
    }

    @Override
    public Email getEmail() {
        return this.email;
    }

    @Override
    public String getLastname() {
        return this.lastname;
    }

    @Override
    public String getFirstname() {
        return this.firstname;
    }

    @Override
    public Password getPassword() {
        return this.password;
    }
}
