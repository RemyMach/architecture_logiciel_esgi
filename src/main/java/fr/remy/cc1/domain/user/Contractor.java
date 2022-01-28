package fr.remy.cc1.domain.user;

import fr.remy.cc1.domain.company.Company;
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
        return new Contractor(userId, lastname, firstname, email,  password, company);
    }

    public UserId getUserId() {
        return userId;
    }

    public Company getCompany() {
        return company;
    }

    @Override
    public Email getEmail() {
        return null;
    }

    @Override
    public String getLastname() {
        return null;
    }

    @Override
    public String getFirstname() {
        return null;
    }

    @Override
    public Password getPassword() {
        return null;
    }
}
