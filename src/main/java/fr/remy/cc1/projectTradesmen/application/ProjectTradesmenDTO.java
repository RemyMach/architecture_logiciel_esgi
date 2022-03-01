package fr.remy.cc1.projectTradesmen.application;

import fr.remy.cc1.projectTradesmen.domain.ProjectTradesmenId;
import fr.remy.cc1.projectTradesmen.domain.ProjectTradesmenStateHistory;

import java.util.Objects;

public final class ProjectTradesmenDTO {
    public final ProjectTradesmenId projectTradesmenId;
    public final ProjectTradesmenStateHistory history;

    public ProjectTradesmenDTO(ProjectTradesmenId projectTradesmenId, ProjectTradesmenStateHistory history) {
        this.projectTradesmenId = Objects.requireNonNull(projectTradesmenId);
        this.history = Objects.requireNonNull(history);
    }

    public ProjectTradesmenStateHistory getHistory() {
        return history;
    }
}
