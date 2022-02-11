package fr.remy.cc1.project.domain.project;

import fr.remy.cc1.infrastructure.exceptions.NoSuchEntityException;

import java.util.List;

public interface Projects {
    void save(Project project);

    Project byId(ProjectId projectId) throws NoSuchEntityException;

    ProjectId nextIdentity();

    List<Project> findAll();
}
