package fr.remy.cc1.domain.project;

import fr.remy.cc1.domain.duration.Duration;
import fr.remy.cc1.domain.location.Location;
import fr.remy.cc1.domain.payment.Money;
import fr.remy.cc1.domain.skill.Skill;
import fr.remy.cc1.domain.trades.Trade;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class ProjectCredentials {

    private final ProjectId projectId;
    private final List<Trade> tradeList;
    private final List<Skill> skills;
    private final Money budget;
    private final Location location;
    private final Duration duration;

    private ProjectCredentials(ProjectId projectId, List<Trade> tradeList, List<Skill> skills, Money budget, Location location, Duration duration) {
        this.projectId = projectId;
        this.tradeList = new ArrayList<>(tradeList);
        this.skills = new ArrayList<>(skills);
        this.budget = budget;
        this.location = location;
        this.duration = duration;
    }

    public static ProjectCredentials of(ProjectId projectId, List<Trade> tradeList, List<Skill> skills, Money budget, Location location, Duration duration) {
        return new ProjectCredentials(projectId, tradeList, skills, budget, location, duration);
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
        ProjectCredentials that = (ProjectCredentials) o;
        return Objects.equals(tradeList, that.tradeList) && Objects.equals(skills, that.skills) && Objects.equals(budget, that.budget) && Objects.equals(location, that.location) && Objects.equals(duration, that.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tradeList, skills, budget, location, duration);
    }

    @Override
    public String toString() {
        return "ProjectCredentials{" +
                "tradesList=" + tradeList +
                ", skills=" + skills +
                ", budget=" + budget +
                ", location=" + location +
                ", duration=" + duration +
                '}';
    }
}
