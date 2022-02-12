package fr.remy.cc1.project.domain.project;

import fr.remy.cc1.certificate.domain.skill.Skill;
import fr.remy.cc1.legacy.domain.trades.Trade;
import fr.remy.cc1.project.domain.duration.Duration;
import fr.remy.cc1.project.domain.location.Location;
import fr.remy.cc1.subscription.domain.Money;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class ProjectRequirements {

    private final ProjectId projectId;
    private final List<Trade> tradeList;
    private final List<Skill> skills;
    private final Money budget;
    private final Location location;
    private final Duration duration;


    private ProjectRequirements(ProjectId projectId, List<Trade> tradeList, List<Skill> skills, Money budget, Location location, Duration duration) {
        this.projectId = projectId;
        this.tradeList = new ArrayList<>(tradeList);
        this.skills = new ArrayList<>(skills);
        this.budget = budget;
        this.location = location;
        this.duration = duration;
    }

    public static ProjectRequirements of(ProjectId projectId, List<Trade> tradeList, List<Skill> skills, Money budget, Location location, Duration duration) {
        return new ProjectRequirements(projectId, tradeList, skills, budget, location, duration);
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
        return Objects.equals(tradeList, that.tradeList) && Objects.equals(skills, that.skills) && Objects.equals(budget, that.budget) && Objects.equals(location, that.location) && Objects.equals(duration, that.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tradeList, skills, budget, location, duration);
    }

    @Override
    public String toString() {
        return "ProjectRequirements{" +
                "tradesList=" + tradeList +
                ", skills=" + skills +
                ", budget=" + budget +
                ", location=" + location +
                ", duration=" + duration +
                '}';
    }
}
