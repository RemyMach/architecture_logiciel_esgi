package fr.remy.cc1.infrastructure.project;

import fr.remy.cc1.project.domain.project.Project;
import fr.remy.cc1.project.domain.project.ProjectId;
import fr.remy.cc1.project.domain.project.Projects;
import fr.remy.cc1.infrastructure.exceptions.InfrastructureExceptionsDictionary;
import fr.remy.cc1.infrastructure.exceptions.NoSuchEntityException;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryProjects implements Projects {

    private final AtomicInteger counter = new AtomicInteger(0);
    private final Map<ProjectId, Project> projectsData = new ConcurrentHashMap<>();

    @Override
    public void save(Project project) {
        projectsData.put(project.getProjectId(), project);
    }

    @Override
    public Project byId(ProjectId projectId) throws NoSuchEntityException {
        final Project project = projectsData.get(projectId);
        if (project == null) {
            throw new NoSuchEntityException(InfrastructureExceptionsDictionary.PROJECT_NOT_FOUND.getErrorCode(), InfrastructureExceptionsDictionary.PROJECT_NOT_FOUND.getMessage());
        }
        return project;
    }

    @Override
    public ProjectId nextIdentity() {
        return ProjectId.of(counter.incrementAndGet());
    }

    @Override
    public List<Project> findAll() {
        return List.copyOf(projectsData.values());
    }
}
