package fr.remy.cc1.projectTradesmen.application;

import fr.remy.cc1.domain.User;
import fr.remy.cc1.domain.UserId;
import fr.remy.cc1.domain.money.Money;
import fr.remy.cc1.domain.trade.TradeJobs;
import fr.remy.cc1.infrastructure.exceptions.NoSuchEntityException;
import fr.remy.cc1.kernel.CommandHandler;
import fr.remy.cc1.kernel.error.ExceptionsDictionary;
import fr.remy.cc1.kernel.error.UserCategoryValidatorException;
import fr.remy.cc1.kernel.error.ValidationException;
import fr.remy.cc1.kernel.event.Event;
import fr.remy.cc1.kernel.event.EventBus;
import fr.remy.cc1.member.domain.user.UserCategory;
import fr.remy.cc1.member.domain.user.Users;
import fr.remy.cc1.project.domain.project.ProjectId;
import fr.remy.cc1.project.domain.project.Projects;
import fr.remy.cc1.projectTradesmen.domain.*;
import fr.remy.cc1.projectTradesmen.domain.TrademenInformations.DateRange;
import fr.remy.cc1.projectTradesmen.domain.TrademenInformations.TradesmenInformations;
import fr.remy.cc1.subscription.domain.currency.Currency;

import java.math.BigDecimal;
import java.sql.Date;
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
    public ProjectTradesmenId handle(CreateProjectTradesmen command) throws NoSuchEntityException, ValidationException {
        final ProjectTradesmenId projectTradesmenId = projectsTradesmen.nextIdentity();
        final ProjectId projectId = ProjectId.of(Integer.parseInt(command.projectId));
        UserId userId;
        TradeJobs tradeJobs;
        Money dailyRate;
        DateRange dateRange;

        try {
            this.projects.byId(projectId);
        } catch (NoSuchEntityException ignored) {
        }

        List<TradesmenInformations> tradesmenInformations = new ArrayList<>();
        User user;
        for (int i = 0; i < command.tradesmenId.size(); i++) {
            userId = UserId.of(Integer.parseInt(command.tradesmenId.get(i)));
            tradeJobs = TradeJobs.getTradeFromJobName(command.tradeJob.get(i));
            dailyRate = Money.of(BigDecimal.valueOf(Long.parseLong(command.dailyRate.get(i))), Currency.valueOf(command.currency));
            dateRange = DateRange.of(Date.valueOf(command.startDates.get(i)), Date.valueOf(command.endDates.get(i)));

            try {
                user = users.byId(userId);
                if(!user.getUserCategory().equals(UserCategory.TRADESMAN)) {
                    throw new UserCategoryValidatorException(ExceptionsDictionary.USER_CATEGORY_NOT_VALID.getErrorCode(), ExceptionsDictionary.USER_CATEGORY_NOT_VALID.getMessage());
                }
            } catch (NoSuchEntityException ignored) {
            }

            tradesmenInformations.add(TradesmenInformations.of(userId, tradeJobs, dailyRate, dateRange));
        }

        ProjectTradesmenCandidate candidates = ProjectTradesmenCandidate.of(projectId, tradesmenInformations);
        ProjectTradesmen projectTradesmen = ProjectTradesmen.of(projectTradesmenId, candidates.projectId, candidates.tradesmenInformations, ProjectTradesmenState.CREATED);

        this.projectsTradesmen.save(projectTradesmen);
        this.eventBus.send(RegisteredProjectTradesmenRequirementsEvent.withProjectId(new ProjectTradesmenDTO(projectTradesmenId, projectTradesmen.getHistory())));
        System.out.println(this.projectsTradesmen.findAll());
        return projectTradesmenId;
    }
}
