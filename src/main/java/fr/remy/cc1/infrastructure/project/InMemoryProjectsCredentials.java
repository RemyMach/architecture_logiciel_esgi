package fr.remy.cc1.infrastructure.project;

import fr.remy.cc1.domain.project.ProjectCredentials;
import fr.remy.cc1.domain.project.ProjectId;
import fr.remy.cc1.domain.project.ProjectsCredentials;
import fr.remy.cc1.infrastructure.exceptions.InfrastructureExceptionsDictionary;
import fr.remy.cc1.infrastructure.exceptions.NoSuchEntityException;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryProjectsCredentials implements ProjectsCredentials {

    private final Map<ProjectId, ProjectCredentials> projectsCredentialsData = new ConcurrentHashMap<>();

    @Override
    public void save(ProjectCredentials projectCredentials) {
        projectsCredentialsData.put(projectCredentials.getProjectId(), projectCredentials);
    }

    @Override
    public ProjectCredentials byId(ProjectId projectId) throws NoSuchEntityException {
        final ProjectCredentials projectCredentials = projectsCredentialsData.get(projectId);
        if (projectCredentials == null) {
            throw new NoSuchEntityException(InfrastructureExceptionsDictionary.PROJECT_CREDENTIALS_NOT_FOUND.getErrorCode(), InfrastructureExceptionsDictionary.PROJECT_CREDENTIALS_NOT_FOUND.getMessage());
        }
        return projectCredentials;
    }

    @Override
    public List<ProjectCredentials> findAll() {
        return List.copyOf(projectsCredentialsData.values());
    }
}
