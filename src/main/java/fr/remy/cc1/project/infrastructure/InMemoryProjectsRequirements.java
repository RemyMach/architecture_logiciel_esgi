package fr.remy.cc1.project.infrastructure;

import fr.remy.cc1.project.domain.project.ProjectId;
import fr.remy.cc1.project.domain.project.ProjectRequirements;
import fr.remy.cc1.project.domain.project.ProjectsRequirements;
import fr.remy.cc1.shared.infrastructure.exceptions.InfrastructureExceptionsDictionary;
import fr.remy.cc1.shared.infrastructure.exceptions.NoSuchEntityException;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryProjectsRequirements implements ProjectsRequirements {

    private final Map<ProjectId, ProjectRequirements> projectsRequirementsData = new ConcurrentHashMap<>();

    @Override
    public void save(ProjectRequirements projectRequirements) {
        projectsRequirementsData.put(projectRequirements.getProjectId(), projectRequirements);
    }

    @Override
    public ProjectRequirements byId(ProjectId projectId) throws NoSuchEntityException {
        final ProjectRequirements projectRequirements = projectsRequirementsData.get(projectId);
        if (projectRequirements == null) {
            throw new NoSuchEntityException(InfrastructureExceptionsDictionary.PROJECT_REQUIREMENTS_NOT_FOUND.getErrorCode(), InfrastructureExceptionsDictionary.PROJECT_REQUIREMENTS_NOT_FOUND.getMessage());
        }
        return projectRequirements;
    }

    @Override
    public List<ProjectRequirements> findAll() {
        return List.copyOf(projectsRequirementsData.values());
    }
}
