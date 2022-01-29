package fr.remy.cc1.domain.project;

import fr.remy.cc1.domain.duration.Duration;
import fr.remy.cc1.domain.location.Location;
import fr.remy.cc1.domain.payment.Money;
import fr.remy.cc1.domain.skill.Skill;
import fr.remy.cc1.domain.trades.Trade;

import java.util.List;
import java.util.Objects;

public class ProjectCredentialsCandidate {

    public final List<Trade> tradeList;
    public final List<Skill> skills;
    public final Money budget;
    public final Location location;
    public final Duration duration;

    private ProjectCredentialsCandidate(List<Trade> tradeList, List<Skill> skills, Money budget, Location location, Duration duration) {
        this.tradeList = tradeList;
        this.skills = skills;
        this.budget = budget;
        this.location = location;
        this.duration = duration;
    }

    public static ProjectCredentialsCandidate of(List<Trade> tradeList, List<Skill> skills, Money budget, Location location, Duration duration) {
        return new ProjectCredentialsCandidate(tradeList, skills, budget, location, duration);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectCredentialsCandidate that = (ProjectCredentialsCandidate) o;
        return Objects.equals(tradeList, that.tradeList) && Objects.equals(skills, that.skills) && Objects.equals(budget, that.budget) && Objects.equals(location, that.location) && Objects.equals(duration, that.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tradeList, skills, budget, location, duration);
    }

    @Override
    public String toString() {
        return "ProjectCredentialsCandidate{" +
                "tradesList=" + tradeList +
                ", skills=" + skills +
                ", budget=" + budget +
                ", location=" + location +
                ", duration=" + duration +
                '}';
    }
}
