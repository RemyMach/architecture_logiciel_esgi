package fr.remy.cc1.projectTradesmen.application;

import fr.remy.cc1.domain.UserId;
import fr.remy.cc1.infrastructure.exceptions.NoSuchEntityException;
import fr.remy.cc1.kernel.CommandHandler;
import fr.remy.cc1.kernel.event.Event;
import fr.remy.cc1.kernel.event.EventBus;
import fr.remy.cc1.member.domain.user.Users;
import fr.remy.cc1.project.domain.project.ProjectId;
import fr.remy.cc1.project.domain.project.Projects;
import fr.remy.cc1.projectTradesmen.domain.*;

import java.util.ArrayList;
import java.util.List;

public final class CreateProjectTradesmenCommandHandler implements CommandHandler<CreateProjectTradesmen, ProjectTradesmenId> {
    private final ProjectsTradesmen projectsTradesmen;
    private final Projects projects;
    private final Users users;
    private final EventBus<Event> eventBus;

    public CreateProjectTradesmenCommandHandler(ProjectsTradesmen projectsTradesmen, Projects projects, Users users, EventBus<Event> eventBus) {
        this.projectsTradesmen = projectsTradesmen;
        this.projects = projects;
        this.users = users;
        this.eventBus = eventBus;
    }

    @Override
    public ProjectTradesmenId handle(CreateProjectTradesmen command) throws NoSuchEntityException {
        final ProjectTradesmenId projectTradesmenId = projectsTradesmen.nextIdentity();
        final ProjectId projectId = ProjectId.of(Integer.parseInt(command.projectId));

        List<UserId> tradesmen = new ArrayList<>();
        for (String tradesmanId : command.tradesmenId) {
            UserId userId = UserId.of(Integer.parseInt(tradesmanId));
            try {
                users.byId(userId);
            } catch (NoSuchEntityException ignored) {
            }
            tradesmen.add(userId);
        }

        try {
            this.projects.byId(projectId);
        } catch (NoSuchEntityException ignored) {
        }

        ProjectTradesmenCandidate candidates = ProjectTradesmenCandidate.of(projectId, tradesmen);
        ProjectTradesmen projectTradesmen = ProjectTradesmen.of(projectTradesmenId, candidates.projectId, candidates.tradesmenId, ProjectTradesmenState.CREATED);

        this.projectsTradesmen.save(projectTradesmen);
        this.eventBus.send(RegisteredProjectTradesmenRequirementsEvent.withProjectId(new ProjectTradesmenDTO(projectTradesmenId, projectTradesmen.getHistory())));
        System.out.println(this.projectsTradesmen.findAll());
        return projectTradesmenId;
    }
}
