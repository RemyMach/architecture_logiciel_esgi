package fr.remy.cc1.projectTradesmen.application;

import fr.remy.cc1.domain.UserId;
import fr.remy.cc1.infrastructure.exceptions.NoSuchEntityException;
import fr.remy.cc1.kernel.CommandHandler;
import fr.remy.cc1.project.domain.project.ProjectId;
import fr.remy.cc1.projectTradesmen.domain.ProjectTradesmen;
import fr.remy.cc1.projectTradesmen.domain.ProjectTradesmenCandidate;
import fr.remy.cc1.projectTradesmen.domain.ProjectTradesmenId;
import fr.remy.cc1.projectTradesmen.domain.ProjectsTradesmen;

import java.util.ArrayList;
import java.util.List;

public final class CreateProjectTradesmenCommandHandler implements CommandHandler<CreateProjectTradesmen, ProjectTradesmenId> {
    private final ProjectsTradesmen projectsTradesmen;

    public CreateProjectTradesmenCommandHandler(ProjectsTradesmen projectsTradesmen) {
        this.projectsTradesmen = projectsTradesmen;
    }

    @Override
    public ProjectTradesmenId handle(CreateProjectTradesmen command) throws NoSuchEntityException {
        final ProjectTradesmenId projectTradesmenId = projectsTradesmen.nextIdentity();
        final ProjectId projectId = ProjectId.of(Integer.parseInt(command.projectId));

        List<UserId> tradesmen = new ArrayList<>();
        for (String tradesmanId : command.tradesmenId) {
            tradesmen.add(UserId.of(Integer.parseInt(tradesmanId)));
        }

        ProjectTradesmenCandidate candidates = ProjectTradesmenCandidate.of(projectId, tradesmen);
        ProjectTradesmen projectTradesmen = ProjectTradesmen.of(projectTradesmenId, candidates.projectId, candidates.tradesmenId);

        this.projectsTradesmen.save(projectTradesmen);
        System.out.println(this.projectsTradesmen.findAll());
        return projectTradesmenId;
    }
}
