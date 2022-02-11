package fr.remy.cc1.project.domain.project;

import fr.remy.cc1.project.domain.duration.Duration;
import fr.remy.cc1.project.domain.location.Location;
import fr.remy.cc1.subscription.domain.Money;
import fr.remy.cc1.domain.skill.Skill;
import fr.remy.cc1.domain.trades.Trade;

import java.util.List;
import java.util.Objects;

public class ProjectRequirementsCandidate {

    public final List<Trade> tradeList;
    public final List<Skill> skills;
    public final Money budget;
    public final Location location;
    public final Duration duration;

    private ProjectRequirementsCandidate(List<Trade> tradeList, List<Skill> skills, Money budget, Location location, Duration duration) {
        this.tradeList = tradeList;
        this.skills = skills;
        this.budget = budget;
        this.location = location;
        this.duration = duration;
    }

    public static ProjectRequirementsCandidate of(List<Trade> tradeList, List<Skill> skills, Money budget, Location location, Duration duration) {
        return new ProjectRequirementsCandidate(tradeList, skills, budget, location, duration);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectRequirementsCandidate that = (ProjectRequirementsCandidate) o;
        return Objects.equals(tradeList, that.tradeList) && Objects.equals(skills, that.skills) && Objects.equals(budget, that.budget) && Objects.equals(location, that.location) && Objects.equals(duration, that.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tradeList, skills, budget, location, duration);
    }

    @Override
    public String toString() {
        return "ProjectRequirementsCandidate{" +
                "tradesList=" + tradeList +
                ", skills=" + skills +
                ", budget=" + budget +
                ", location=" + location +
                ", duration=" + duration +
                '}';
    }
}
