package fr.remy.cc1.project.application;

import fr.remy.cc1.project.domain.project.ProjectId;
import fr.remy.cc1.project.domain.project.ProjectStateHistory;

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
