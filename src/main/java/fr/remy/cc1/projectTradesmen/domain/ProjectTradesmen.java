package fr.remy.cc1.projectTradesmen.domain;

import fr.remy.cc1.project.domain.project.ProjectId;
import fr.remy.cc1.projectTradesmen.domain.TrademenInformations.TradesmenInformations;

import java.util.List;
import java.util.Objects;

public final class ProjectTradesmen {
    private final ProjectTradesmenId projectTradesmenId;
    private final ProjectId projectId;
    private final List<TradesmenInformations> tradesmenInformations;
    private final ProjectTradesmenStateHistory history;

    private ProjectTradesmen(ProjectTradesmenId projectTradesmenId, ProjectId projectId, List<TradesmenInformations> tradesmenInformations, ProjectTradesmenState initialState) {
        this.projectTradesmenId = projectTradesmenId;
        this.projectId = projectId;
        this.tradesmenInformations = tradesmenInformations;
        this.history = ProjectTradesmenStateHistory.create(initialState);
    }

    public static ProjectTradesmen of(ProjectTradesmenId projectTradesmenId, ProjectId projectId, List<TradesmenInformations> tradesmenInformations, ProjectTradesmenState initialState) {
        return new ProjectTradesmen(projectTradesmenId, projectId, tradesmenInformations, initialState);
    }

    public ProjectTradesmenId getProjectTradesmenId() {
        return projectTradesmenId;
    }

    public ProjectTradesmenStateHistory getHistory() {
        return history;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProjectTradesmen)) return false;
        ProjectTradesmen that = (ProjectTradesmen) o;
        return Objects.equals(getProjectTradesmenId(), that.getProjectTradesmenId()) && Objects.equals(projectId, that.projectId) && Objects.equals(tradesmenInformations, that.tradesmenInformations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProjectTradesmenId(), projectId, tradesmenInformations);
    }

    @Override
    public String toString() {
        return "ProjectTradesmen{" +
                "projectTradesmenId=" + projectTradesmenId +
                ", projectId=" + projectId +
                ", tradesmenInformations=" + tradesmenInformations +
                ", history=" + history +
                '}';
    }
}
