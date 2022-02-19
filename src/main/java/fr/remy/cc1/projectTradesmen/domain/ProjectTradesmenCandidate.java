package fr.remy.cc1.projectTradesmen.domain;

import fr.remy.cc1.domain.UserId;
import fr.remy.cc1.project.domain.project.ProjectId;

import java.util.List;

public final class ProjectTradesmenCandidate {
    public final ProjectId projectId;
    public final List<UserId> tradesmenId;

    private ProjectTradesmenCandidate(ProjectId projectId, List<UserId> tradesmenId) {
        this.projectId = projectId;
        this.tradesmenId = tradesmenId;
    }

    public static ProjectTradesmenCandidate of(ProjectId projectId, List<UserId> tradesmenId) {
        return new ProjectTradesmenCandidate(projectId, tradesmenId);
    }
}
