package fr.remy.cc1.domain.project;

import fr.remy.cc1.infrastructure.exceptions.NoSuchEntityException;

import java.util.List;

public interface ProjectsCredentials {
    void save(ProjectCredentials projectCredentials);

    ProjectCredentials byId(ProjectId projectId) throws NoSuchEntityException;

    List<ProjectCredentials> findAll();
}
