package fr.remy.cc1.domain.user;

import fr.remy.cc1.kernel.error.ValidationException;

public final class Tradesman implements IUser {

    private final TradesmanId tradesmanId;
    private final String lastname;
    private final String firstname;
    private final Email email;
    private final Password password;

    private Tradesman(TradesmanId tradesmanId, String lastname, String firstname, Email email, Password password) {
        this.tradesmanId = tradesmanId;
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.password = password;
    }

    public static Tradesman of(TradesmanId tradesmanId, String lastname, String firstname, Email email, Password password) throws ValidationException {
        return new Tradesman(tradesmanId, lastname, firstname, email,  password);
    }

    public TradesmanId getTradesmanId() {
        return tradesmanId;
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
