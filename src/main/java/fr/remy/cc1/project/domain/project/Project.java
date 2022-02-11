package fr.remy.cc1.project.domain.project;

import java.util.Objects;

public final class Project {

    private final ProjectId projectId;
    private ProjectStateHistory history;
    private final String name;
    private final String description;

    private Project(ProjectId projectId, ProjectState initialState, String name, String description) {
        this.projectId = projectId;
        this.history = ProjectStateHistory.create(initialState);
        this.name = name;
        this.description = description;
    }

    public static Project of(ProjectId projectId, ProjectState initialState, String name, String description) {
        return new Project(projectId, initialState, name, description);
    }

    public void changeState(ProjectState projectState) {
        this.history = this.history.append(projectState);
    }

    public ProjectId getProjectId() {
        return this.projectId;
    }

    public ProjectStateHistory getHistory() {
        return this.history;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return Objects.equals(projectId, project.projectId) && Objects.equals(history, project.history) && Objects.equals(name, project.name) && Objects.equals(description, project.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId, history, name, description);
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectId=" + projectId +
                ", history=" + history +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
