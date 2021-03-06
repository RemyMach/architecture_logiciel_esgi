package fr.remy.cc1.member.domain.user.Tradesman;

import fr.remy.cc1.kernel.error.ValidationException;
import fr.remy.cc1.member.domain.user.*;
import fr.remy.cc1.project.domain.location.Country;
import fr.remy.cc1.shared.domain.UserId;
import fr.remy.cc1.shared.domain.money.Money;
import fr.remy.cc1.shared.domain.skill.Skill;
import fr.remy.cc1.shared.domain.trade.TradeJobs;

import java.util.List;

public final class Tradesman implements IUser {

    private final UserId userId;
    private final String lastname;
    private final String firstname;
    private final Email email;
    private final Password password;

    private final List<TradeJobs> trade;
    private final List<Skill> skills;
    private final List<Country> availabilityZone;
    private final Money dailyRate;

    private Tradesman(UserId userId, String lastname, String firstname, Email email, Password password, List<TradeJobs> trade, List<Country> availabilityZone, Money dailyRate, List<Skill> skills) {
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

    public static Tradesman of(UserId userId, String lastname, String firstname, Email email, Password password, List<TradeJobs> trade, List<Country> availabilityZone, Money dailyRate, List<Skill> skills) throws ValidationException {
        UserCandidate userCandidate = UserCandidate.of(lastname, firstname, email, password, UserCategory.TRADESMAN);
        return new Tradesman(userId, userCandidate.lastname, userCandidate.firstname, userCandidate.email,  userCandidate.password, trade, availabilityZone, dailyRate, skills);
    }

    @Override
    public UserId getUserId() {
        return userId;
    }

    public List<TradeJobs> getTrades() {
        return trade;
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
