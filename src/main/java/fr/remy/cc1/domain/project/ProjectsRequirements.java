package fr.remy.cc1.domain.project;

import fr.remy.cc1.infrastructure.exceptions.NoSuchEntityException;

import java.util.List;

public interface ProjectsRequirements {
    void save(ProjectRequirements projectRequirements);

    ProjectRequirements byId(ProjectId projectId) throws NoSuchEntityException;

    List<ProjectRequirements> findAll();
}
