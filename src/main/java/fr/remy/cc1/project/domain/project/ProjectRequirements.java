package fr.remy.cc1.project.domain.project;

import fr.remy.cc1.certificate.domain.skill.Skill;
import fr.remy.cc1.legacy.domain.trades.Trade;
import fr.remy.cc1.project.domain.duration.Duration;
import fr.remy.cc1.project.domain.location.Location;
import fr.remy.cc1.subscription.domain.Money;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public final class ProjectRequirements {

    private final ProjectId projectId;
    private final List<Trade> tradeList;
    private final List<Skill> skills;
    private final Map<Trade, Money> tradesBudget;
    private final Map<Trade, Duration> tradesDuration;
    private final Location location;


    private ProjectRequirements(ProjectId projectId, List<Trade> tradeList, List<Skill> skills, Map<Trade, Money> tradesBudget, Map<Trade, Duration> tradesDuration, Location location) {
        this.projectId = projectId;
        this.tradeList = new ArrayList<>(tradeList);
        this.skills = new ArrayList<>(skills);
        this.tradesBudget = new ConcurrentHashMap<>(tradesBudget);
        this.tradesDuration = new ConcurrentHashMap<>(tradesDuration);
        this.location = location;
    }

    public static ProjectRequirements of(ProjectId projectId, List<Trade> tradeList, List<Skill> skills, Map<Trade, Money> tradesBudget, Map<Trade, Duration> tradesDuration, Location location) {
        return new ProjectRequirements(projectId, tradeList, skills, tradesBudget, tradesDuration, location);
    }

    public void addTrades(Trade trade) {
        this.tradeList.add(trade);
    }

    public void addSkill(Skill skill) {
        this.skills.add(skill);
    }

    public ProjectId getProjectId() {
        return projectId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectRequirements that = (ProjectRequirements) o;
        return Objects.equals(tradeList, that.tradeList) && Objects.equals(skills, that.skills) && Objects.equals(tradesBudget, that.tradesBudget) && Objects.equals(tradesDuration, that.tradesDuration)&& Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tradeList, skills, tradesBudget, tradesDuration, location);
    }

    @Override
    public String toString() {
        return "ProjectRequirements{" +
                "projectId=" + projectId +
                ", tradeList=" + tradeList +
                ", skills=" + skills +
                ", tradesBudget=" + tradesBudget +
                ", tradesDuration=" + tradesDuration +
                ", location=" + location +
                '}';
    }
}
