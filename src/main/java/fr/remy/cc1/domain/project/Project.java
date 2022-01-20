package fr.remy.cc1.domain.project;

public final class Project {

    private final ProjectId projectId;
    private final ProjectStateHistory history;

    private Project(ProjectId projectId, ProjectStateHistory history) {
        this.projectId = projectId;
        this.history = history;
    }

    public static Project of(ProjectId projectId, ProjectStateHistory history) {
        return new Project(projectId, history);
    }

    public void changeState(ProjectState projectState) {
        this.history.append(projectState);
    }

    public ProjectId getProjectId() {
        return projectId;
    }
}
