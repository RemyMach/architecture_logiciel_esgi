package fr.remy.cc1.infrastructure.project;

import fr.remy.cc1.project.domain.project.ProjectRequirements;
import fr.remy.cc1.project.domain.project.ProjectId;
import fr.remy.cc1.project.domain.project.ProjectsRequirements;
import fr.remy.cc1.infrastructure.exceptions.InfrastructureExceptionsDictionary;
import fr.remy.cc1.infrastructure.exceptions.NoSuchEntityException;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryProjectsRequirements implements ProjectsRequirements {

    private final Map<ProjectId, ProjectRequirements> projectsRquirementsData = new ConcurrentHashMap<>();

    @Override
    public void save(ProjectRequirements projectRequirements) {
        projectsRquirementsData.put(projectRequirements.getProjectId(), projectRequirements);
    }

    @Override
    public ProjectRequirements byId(ProjectId projectId) throws NoSuchEntityException {
        final ProjectRequirements projectRequirements = projectsRquirementsData.get(projectId);
        if (projectRequirements == null) {
            throw new NoSuchEntityException(InfrastructureExceptionsDictionary.PROJECT_REQUIREMENTS_NOT_FOUND.getErrorCode(), InfrastructureExceptionsDictionary.PROJECT_REQUIREMENTS_NOT_FOUND.getMessage());
        }
        return projectRequirements;
    }

    @Override
    public List<ProjectRequirements> findAll() {
        return List.copyOf(projectsRquirementsData.values());
    }
}
