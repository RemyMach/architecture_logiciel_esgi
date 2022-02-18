package fr.remy.cc1.projectTradesmen.application;

import fr.remy.cc1.domain.UserId;
import fr.remy.cc1.project.domain.project.ProjectId;
import fr.remy.cc1.projectTradesmen.domain.ProjectTradesmenId;

import java.util.List;

public final class ProjectTradesmenDTO {
    public final ProjectTradesmenId projectTradesmenId;
    public final ProjectId projectId;
    public final List<UserId> tradesmenId;

    public ProjectTradesmenDTO(ProjectTradesmenId projectTradesmenId, ProjectId projectId, List<UserId> tradesmenId) {
        this.projectTradesmenId = projectTradesmenId;
        this.projectId = projectId;
        this.tradesmenId = tradesmenId;
    }
}
