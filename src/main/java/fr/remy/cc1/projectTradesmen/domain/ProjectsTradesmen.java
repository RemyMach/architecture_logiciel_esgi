package fr.remy.cc1.projectTradesmen.domain;

import fr.remy.cc1.shared.infrastructure.exceptions.NoSuchEntityException;

import java.util.List;

public interface ProjectsTradesmen {
    void save(ProjectTradesmen projectTradesmen);

    ProjectTradesmen byId(ProjectTradesmenId projectTradesmenId) throws NoSuchEntityException;

    ProjectTradesmenId nextIdentity();

    List<ProjectTradesmen> findAll();
}
