package fr.remy.cc1.project.domain.project;

import fr.remy.cc1.domain.money.Money;
import fr.remy.cc1.domain.skill.Skill;
import fr.remy.cc1.domain.trade.TradeJobs;
import fr.remy.cc1.project.domain.duration.Duration;
import fr.remy.cc1.project.domain.location.Location;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class ProjectRequirementsCandidate {

    public final List<TradeJobs> tradeList;
    public final List<Skill> skills;
    public final Map<TradeJobs, Money> tradesBudget;
    public final Map<TradeJobs, Duration> tradesDuration;
    public final Location location;

    private ProjectRequirementsCandidate(List<TradeJobs> tradeList, List<Skill> skills, Map<TradeJobs, Money> tradesBudget, Map<TradeJobs, Duration> tradesDuration, Location location) {
        this.tradeList = tradeList;
        this.skills = skills;
        this.tradesBudget = new ConcurrentHashMap<>(tradesBudget);
        this.tradesDuration = new ConcurrentHashMap<>(tradesDuration);
        this.location = location;
    }

    public static ProjectRequirementsCandidate of(List<TradeJobs> tradeList, List<Skill> skills, Map<TradeJobs, Money> tradesBudget, Map<TradeJobs, Duration> tradesDuration, Location location) {
        return new ProjectRequirementsCandidate(tradeList, skills, tradesBudget, tradesDuration, location);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectRequirementsCandidate that = (ProjectRequirementsCandidate) o;
        return Objects.equals(tradeList, that.tradeList) && Objects.equals(skills, that.skills) && Objects.equals(tradesBudget, that.tradesBudget) && Objects.equals(tradesDuration, that.tradesDuration) && Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tradeList, skills, tradesBudget, tradesDuration, location);
    }

    @Override
    public String toString() {
        return "ProjectRequirementsCandidate{" +
                "tradeList=" + tradeList +
                ", skills=" + skills +
                ", tradesBudget=" + tradesBudget +
                ", tradesDuration=" + tradesDuration +
                ", location=" + location +
                '}';
    }
}
