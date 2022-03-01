package fr.remy.cc1.projectTradesmen.domain;

import fr.remy.cc1.project.domain.project.ProjectId;

import java.util.List;
import java.util.Objects;

public final class ProjectTradesmenCandidate {
    public final ProjectId projectId;
    public final List<TradesmenInformations> tradesmenInformations;

    private ProjectTradesmenCandidate(ProjectId projectId, List<TradesmenInformations> tradesmenInformations) {
        this.projectId = Objects.requireNonNull(projectId);
        this.tradesmenInformations = Objects.requireNonNull(tradesmenInformations);
    }

    public static ProjectTradesmenCandidate of(ProjectId projectId, List<TradesmenInformations> tradesmenInformations) {
        return new ProjectTradesmenCandidate(projectId, tradesmenInformations);
    }
}
