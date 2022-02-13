package fr.remy.cc1.project.domain.project;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public final class ProjectStateHistory {
    private final List<ProjectState> history;

    private ProjectStateHistory(List<ProjectState> history) {
        this.history = history;
    }

    public static ProjectStateHistory create(ProjectState userState) {
        userState.setDate(Date.from(Instant.now()));
        return new ProjectStateHistory(List.of(userState));
    }

    public ProjectStateHistory append(ProjectState userState) {
        List<ProjectState> newStates = new ArrayList<>(history);
        userState.setDate(Date.from(Instant.now()));
        newStates.add(userState);
        return new ProjectStateHistory(newStates);
    }

    public List<ProjectState> getStates() {
        return List.copyOf(history);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectStateHistory that = (ProjectStateHistory) o;
        return Objects.equals(history, that.history);
    }

    @Override
    public int hashCode() {
        return Objects.hash(history);
    }

    @Override
    public String toString() {
        return "ProjectStateHistory{" +
                "history=" + history +
                '}';
    }
}
