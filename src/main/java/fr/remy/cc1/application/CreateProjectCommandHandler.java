package fr.remy.cc1.application;

import fr.remy.cc1.domain.project.*;
import fr.remy.cc1.kernel.CommandHandler;
import fr.remy.cc1.kernel.error.ValidationException;

public class CreateProjectCommandHandler implements CommandHandler<CreateProject, ProjectId> {

    private final Projects projects;

    public CreateProjectCommandHandler(Projects projects) {
        this.projects = projects;
    }

    @Override
    public ProjectId handle(CreateProject createProject) throws ValidationException {
        ProjectCandidate projectCandidate = ProjectCandidate.of(createProject.name, createProject.description);
        final ProjectId projectId = projects.nextIdentity();
        Project project = Project.of(projectId, ProjectState.PENDING, projectCandidate.name, projectCandidate.description);
        this.projects.save(project);
        return projectId;
    }
}
