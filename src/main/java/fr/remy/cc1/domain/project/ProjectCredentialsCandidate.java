package fr.remy.cc1.domain.project;

import fr.remy.cc1.domain.duration.Duration;
import fr.remy.cc1.domain.location.Location;
import fr.remy.cc1.domain.payment.Money;
import fr.remy.cc1.domain.skill.Skill;
import fr.remy.cc1.domain.trades.Trades;

import java.util.List;
import java.util.Objects;

public class ProjectCredentialsCandidate {

    public final List<Trades> tradesList;
    public final List<Skill> skills;
    public final Money budget;
    public final Location location;
    public final Duration duration;

    private ProjectCredentialsCandidate(List<Trades> tradesList, List<Skill> skills, Money budget, Location location, Duration duration) {
        this.tradesList = tradesList;
        this.skills = skills;
        this.budget = budget;
        this.location = location;
        this.duration = duration;
    }

    public static ProjectCredentialsCandidate of(List<Trades> tradesList, List<Skill> skills, Money budget, Location location, Duration duration) {
        return new ProjectCredentialsCandidate(tradesList, skills, budget, location, duration);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectCredentialsCandidate that = (ProjectCredentialsCandidate) o;
        return Objects.equals(tradesList, that.tradesList) && Objects.equals(skills, that.skills) && Objects.equals(budget, that.budget) && Objects.equals(location, that.location) && Objects.equals(duration, that.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tradesList, skills, budget, location, duration);
    }

    @Override
    public String toString() {
        return "ProjectCredentialsCandidate{" +
                "tradesList=" + tradesList +
                ", skills=" + skills +
                ", budget=" + budget +
                ", location=" + location +
                ", duration=" + duration +
                '}';
    }
}
