package fr.remy.cc1.projectTradesmen.application;

import fr.remy.cc1.domain.UserId;
import fr.remy.cc1.infrastructure.exceptions.NoSuchEntityException;
import fr.remy.cc1.kernel.CommandHandler;
import fr.remy.cc1.member.domain.user.Users;
import fr.remy.cc1.project.domain.project.ProjectId;
import fr.remy.cc1.project.domain.project.Projects;
import fr.remy.cc1.projectTradesmen.domain.ProjectTradesmen;
import fr.remy.cc1.projectTradesmen.domain.ProjectTradesmenCandidate;
import fr.remy.cc1.projectTradesmen.domain.ProjectTradesmenId;
import fr.remy.cc1.projectTradesmen.domain.ProjectsTradesmen;

import java.util.ArrayList;
import java.util.List;

public final class CreateProjectTradesmenCommandHandler implements CommandHandler<CreateProjectTradesmen, ProjectTradesmenId> {
    private final ProjectsTradesmen projectsTradesmen;
    private final Projects projects;
    private final Users users;

    public CreateProjectTradesmenCommandHandler(ProjectsTradesmen projectsTradesmen, Projects projects, Users users) {
        this.projectsTradesmen = projectsTradesmen;
        this.projects = projects;
        this.users = users;
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
            }
            catch (NoSuchEntityException ignored) {}
            tradesmen.add(userId);
        }

        try {
            this.projects.byId(projectId);
        }
        catch (NoSuchEntityException ignored) {}

        ProjectTradesmenCandidate candidates = ProjectTradesmenCandidate.of(projectId, tradesmen);
        ProjectTradesmen projectTradesmen = ProjectTradesmen.of(projectTradesmenId, candidates.projectId, candidates.tradesmenId);

        this.projectsTradesmen.save(projectTradesmen);
        System.out.println(this.projectsTradesmen.findAll());
        return projectTradesmenId;
    }
}
