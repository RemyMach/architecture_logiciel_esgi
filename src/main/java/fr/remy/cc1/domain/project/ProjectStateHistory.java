package fr.remy.cc1.domain.project;

import java.util.ArrayList;
import java.util.List;

public class ProjectStateHistory {
    private final List<ProjectState> history;

    private ProjectStateHistory(List<ProjectState> history) {
        this.history = history;
    }

    public static ProjectStateHistory create(ProjectState userState) {
        return new ProjectStateHistory(List.of(userState));
    }

    public ProjectStateHistory append(ProjectState userState) {
        List<ProjectState> newStates = new ArrayList<>(history);
        newStates.add(userState);
        return new ProjectStateHistory(newStates);
    }

    public List<ProjectState> getStates() {
        return List.copyOf(history);
    }
}
