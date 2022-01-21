package fr.remy.cc1.application;

import fr.remy.cc1.domain.project.ProjectId;
import fr.remy.cc1.domain.project.ProjectStateHistory;

public final class ProjectDTO {
    public final ProjectId projectId;
    public final ProjectStateHistory history;

    public ProjectDTO(ProjectId projectId, ProjectStateHistory history) {
        this.projectId = projectId;
        this.history = history;
    }

    public ProjectStateHistory getHistory() {
        return history;
    }
}
