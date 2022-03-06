package fr.remy.cc1.projectTradesmen.infrastructure;

import fr.remy.cc1.projectTradesmen.domain.ProjectTradesmen;
import fr.remy.cc1.projectTradesmen.domain.ProjectTradesmenId;
import fr.remy.cc1.projectTradesmen.domain.ProjectsTradesmen;
import fr.remy.cc1.shared.infrastructure.exceptions.InfrastructureExceptionsDictionary;
import fr.remy.cc1.shared.infrastructure.exceptions.NoSuchEntityException;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public final class InMemoryProjectTradesmen implements ProjectsTradesmen {
    private final AtomicInteger counter = new AtomicInteger(0);
    private final Map<ProjectTradesmenId, ProjectTradesmen> projectsTradesmenData = new ConcurrentHashMap<>();

    @Override
    public void save(ProjectTradesmen projectTradesmen) {
        projectsTradesmenData.put(projectTradesmen.getProjectTradesmenId(), projectTradesmen);
    }

    @Override
    public ProjectTradesmen byId(ProjectTradesmenId projectTradesmenId) throws NoSuchEntityException {
        final ProjectTradesmen projectTradesmen = projectsTradesmenData.get(projectTradesmenId);
        if (projectTradesmen == null) {
            throw new NoSuchEntityException(InfrastructureExceptionsDictionary.PROJECT_TRADESMEN_NOT_FOUND.getErrorCode(), InfrastructureExceptionsDictionary.PROJECT_TRADESMEN_NOT_FOUND.getMessage());
        }
        return projectTradesmen;
    }

    @Override
    public ProjectTradesmenId nextIdentity() {
        return ProjectTradesmenId.of(counter.incrementAndGet());
    }

    @Override
    public List<ProjectTradesmen> findAll() {
        return List.copyOf(projectsTradesmenData.values());
    }
}