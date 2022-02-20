package fr.remy.cc1.projectTradesmen.application;

import fr.remy.cc1.projectTradesmen.domain.ProjectTradesmenId;
import fr.remy.cc1.projectTradesmen.domain.ProjectTradesmenStateHistory;

public final class ProjectTradesmenDTO {
    public final ProjectTradesmenId projectTradesmenId;
    public final ProjectTradesmenStateHistory history;

    public ProjectTradesmenDTO(ProjectTradesmenId projectTradesmenId, ProjectTradesmenStateHistory history) {
        this.projectTradesmenId = projectTradesmenId;
        this.history = history;
    }

    public ProjectTradesmenStateHistory getHistory() {
        return history;
    }
}
