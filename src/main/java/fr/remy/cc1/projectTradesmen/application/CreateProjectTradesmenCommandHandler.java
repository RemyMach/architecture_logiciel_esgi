package fr.remy.cc1.projectTradesmen.application;

import fr.remy.cc1.kernel.CommandHandler;
import fr.remy.cc1.projectTradesmen.domain.ProjectTradesmen;
import fr.remy.cc1.projectTradesmen.domain.ProjectTradesmenCandidate;
import fr.remy.cc1.projectTradesmen.domain.ProjectTradesmenId;
import fr.remy.cc1.projectTradesmen.domain.ProjectsTradesmen;

public final class CreateProjectTradesmenCommandHandler implements CommandHandler<CreateProjectTradesmen, ProjectTradesmenId>
{
    private final ProjectsTradesmen projectsTradesmen;

    public CreateProjectTradesmenCommandHandler(ProjectsTradesmen projectsTradesmen) {
        this.projectsTradesmen = projectsTradesmen;
    }

    @Override
    public ProjectTradesmenId handle(CreateProjectTradesmen command) {
        final ProjectTradesmenId projectTradesmenId = projectsTradesmen.nextIdentity();
        /*final

        ProjectTradesmenCandidate candidates = ProjectTradesmenCandidate.of(command.projectId, command.tradesmenId);
        ProjectTradesmen projectTradesmen = ProjectTradesmen.of(projectTradesmenId, candidates.projectId, candidates.tradesmenId);

        this.projectsTradesmen.save(projectTradesmen);
        System.out.println(this.projectsTradesmen.findAll());*/
        return projectTradesmenId;
    }
}
