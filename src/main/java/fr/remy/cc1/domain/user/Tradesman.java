package fr.remy.cc1.domain.user;

import fr.remy.cc1.domain.location.Country;
import fr.remy.cc1.domain.payment.Money;
import fr.remy.cc1.domain.skill.Skill;
import fr.remy.cc1.domain.trade.ETrade;
import fr.remy.cc1.kernel.error.ValidationException;

import java.util.List;

public final class Tradesman implements IUser {

    private final UserId userId;
    private final String lastname;
    private final String firstname;
    private final Email email;
    private final Password password;
    private final List<ETrade> trade;
    private final List<Skill> skills;
    private final List<Country> availabilityZone;
    private final Money dailyRate;

    private Tradesman(UserId userId, String lastname, String firstname, Email email, Password password, List<ETrade> trade, List<Country> availabilityZone, Money dailyRate, List<Skill> skills) {
        this.userId = userId;
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.password = password;
        this.trade = trade;
        this.availabilityZone = availabilityZone;
        this.dailyRate = dailyRate;
        this.skills = skills;
    }

    public static Tradesman of(UserId userId, String lastname, String firstname, Email email, Password password, List<ETrade> trade, List<Country> availabilityZone, Money dailyRate, List<Skill> skills) throws ValidationException {
        return new Tradesman(userId, lastname, firstname, email,  password, trade, availabilityZone, dailyRate, skills);
    }

    public UserId getUserId() {
        return userId;
    }

    public List<ETrade> getTrades() {
        return trade;
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
