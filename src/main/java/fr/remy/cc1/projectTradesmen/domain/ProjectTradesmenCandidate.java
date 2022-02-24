package fr.remy.cc1.projectTradesmen.domain;

import fr.remy.cc1.project.domain.project.ProjectId;
import fr.remy.cc1.projectTradesmen.domain.TrademenInformations.TradesmenInformations;

import java.util.List;

public final class ProjectTradesmenCandidate {
    public final ProjectId projectId;
    public final List<TradesmenInformations> tradesmenInformations;

    private ProjectTradesmenCandidate(ProjectId projectId, List<TradesmenInformations> tradesmenInformations) {
        this.projectId = projectId;
        this.tradesmenInformations = tradesmenInformations;
    }

    public static ProjectTradesmenCandidate of(ProjectId projectId, List<TradesmenInformations> tradesmenInformations) {
        return new ProjectTradesmenCandidate(projectId, tradesmenInformations);
    }
}
