package fr.remy.cc1.project.domain.project;

import fr.remy.cc1.shared.infrastructure.exceptions.NoSuchEntityException;

import java.util.List;

public interface ProjectsRequirements {
    void save(ProjectRequirements projectRequirements);

    ProjectRequirements byId(ProjectId projectId) throws NoSuchEntityException;

    List<ProjectRequirements> findAll();
}
