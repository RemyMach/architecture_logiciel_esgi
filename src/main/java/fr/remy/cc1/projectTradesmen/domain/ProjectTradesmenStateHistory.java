package fr.remy.cc1.projectTradesmen.domain;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ProjectTradesmenStateHistory {
    private final List<ProjectTradesmenState> history;

    private ProjectTradesmenStateHistory(List<ProjectTradesmenState> history) {
        this.history = history;
    }

    public static ProjectTradesmenStateHistory create(ProjectTradesmenState userState) {
        userState.setDate(Date.from(Instant.now()));
        return new ProjectTradesmenStateHistory(List.of(userState));
    }

    public ProjectTradesmenStateHistory append(ProjectTradesmenState userState) {
        List<ProjectTradesmenState> newStates = new ArrayList<>(history);
        userState.setDate(Date.from(Instant.now()));
        newStates.add(userState);
        return new ProjectTradesmenStateHistory(newStates);
    }

    public List<ProjectTradesmenState> getStates() {
        return List.copyOf(history);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectTradesmenStateHistory that = (ProjectTradesmenStateHistory) o;
        return Objects.equals(history, that.history);
    }

    @Override
    public int hashCode() {
        return Objects.hash(history);
    }

    @Override
    public String toString() {
        return "ProjectTradesmenStateHistory{" +
                "history=" + history +
                '}';
    }
}
