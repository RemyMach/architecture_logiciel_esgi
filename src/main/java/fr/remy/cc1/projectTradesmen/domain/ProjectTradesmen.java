package fr.remy.cc1.projectTradesmen.domain;

import fr.remy.cc1.domain.UserId;
import fr.remy.cc1.project.domain.project.ProjectId;

import java.util.List;
import java.util.Objects;

public final class ProjectTradesmen {
    private final ProjectTradesmenId projectTradesmenId;
    private final ProjectId projectId;
    private final List<UserId> tradesmenId;

    private ProjectTradesmen(ProjectTradesmenId projectTradesmenId, ProjectId projectId, List<UserId> tradesmenId) {
        this.projectTradesmenId = projectTradesmenId;
        this.projectId = projectId;
        this.tradesmenId = tradesmenId;
    }

    public static ProjectTradesmen of(ProjectTradesmenId projectTradesmenId, ProjectId projectId, List<UserId> tradesmenId) {
        return new ProjectTradesmen(projectTradesmenId, projectId, tradesmenId);
    }

    public ProjectTradesmenId getProjectTradesmenId() {
        return projectTradesmenId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProjectTradesmen)) return false;
        ProjectTradesmen that = (ProjectTradesmen) o;
        return Objects.equals(getProjectTradesmenId(), that.getProjectTradesmenId()) && Objects.equals(projectId, that.projectId) && Objects.equals(tradesmenId, that.tradesmenId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProjectTradesmenId(), projectId, tradesmenId);
    }

    @Override
    public String toString() {
        return "ProjectTradesmen{" +
                "projectTradesmenId=" + projectTradesmenId +
                ", projectId=" + projectId +
                ", tradesmenId=" + tradesmenId +
                '}';
    }
}
