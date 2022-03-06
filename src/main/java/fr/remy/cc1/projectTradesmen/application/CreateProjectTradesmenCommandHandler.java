package fr.remy.cc1.projectTradesmen.application;

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
import fr.remy.cc1.projectTradesmen.domain.dateRange.DateRange;
import fr.remy.cc1.projectTradesmen.domain.dateRange.DateRangeValidationEngine;
import fr.remy.cc1.projectTradesmen.domain.scheduler.TradesmanSchedule;
import fr.remy.cc1.projectTradesmen.domain.scheduler.TradesmanScheduleCandidate;
import fr.remy.cc1.projectTradesmen.domain.scheduler.TradesmanSchedules;
import fr.remy.cc1.shared.domain.User;
import fr.remy.cc1.shared.domain.UserId;
import fr.remy.cc1.shared.domain.money.Money;
import fr.remy.cc1.shared.domain.trade.TradeJobs;
import fr.remy.cc1.shared.infrastructure.exceptions.NoSuchEntityException;
import fr.remy.cc1.subscription.domain.currency.CurrencyCreator;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public final class CreateProjectTradesmenCommandHandler implements CommandHandler<CreateProjectTradesmen, ProjectTradesmenId> {
    private final ProjectsTradesmen projectsTradesmen;
    private final TradesmanSchedules tradesmanSchedules;
    private final Projects projects;
    private final Users users;
    private final EventBus<Event> eventBus;

    public CreateProjectTradesmenCommandHandler(ProjectsTradesmen projectsTradesmen, TradesmanSchedules tradesmanSchedules, Projects projects, Users users, EventBus<Event> eventBus) {
        this.projectsTradesmen = projectsTradesmen;
        this.tradesmanSchedules = tradesmanSchedules;
        this.projects = projects;
        this.users = users;
        this.eventBus = eventBus;
    }

    @Override
    public ProjectTradesmenId handle(CreateProjectTradesmen command) throws NoSuchEntityException, ValidationException {
        final ProjectTradesmenId projectTradesmenId = projectsTradesmen.nextIdentity();
        final ProjectId projectId = ProjectId.of(Integer.parseInt(command.projectId));
        User user;
        UserId userId;
        TradeJobs tradeJobs;
        Money dailyRate;
        DateRange dateRange;

        ProjectTradesmenCandidate projectTradesmenCandidates;
        ProjectTradesmen projectTradesmen;

        TradesmanScheduleCandidate tradesmanScheduleCandidate;
        TradesmanSchedule tradesmanSchedule;

        this.projects.byId(projectId);

        List<TradesmenInformations> tradesmenInformations = new ArrayList<>();
        for (int i = 0; i < command.tradesmenId.size(); i++) {
            userId = UserId.of(Integer.parseInt(command.tradesmenId.get(i)));
            tradeJobs = TradeJobs.getTradeFromJobName(command.tradeJob.get(i));
            dailyRate = Money.of(BigDecimal.valueOf(Long.parseLong(command.dailyRate.get(i))), CurrencyCreator.getValueOf(command.currency));
            dateRange = DateRange.of(Date.valueOf(command.startDates.get(i)), Date.valueOf(command.endDates.get(i)));

            user = users.byId(userId);
            if (!user.getUserCategory().equals(UserCategory.TRADESMAN)) {
                throw new UserCategoryValidatorException(ExceptionsDictionary.USER_CATEGORY_NOT_VALID.getErrorCode(), ExceptionsDictionary.USER_CATEGORY_NOT_VALID.getMessage());
            }

            if (!DateRangeValidationEngine.getInstance().isValid(dateRange)) {
                throw new UserCategoryValidatorException(ExceptionsDictionary.WRONG_DATES_ORDER.getErrorCode(), ExceptionsDictionary.WRONG_DATES_ORDER.getMessage());
            }

            tradesmenInformations.add(TradesmenInformations.of(userId, tradeJobs, dailyRate, dateRange));
            tradesmanScheduleCandidate = TradesmanScheduleCandidate.of(dateRange);

            try {
                tradesmanSchedule = tradesmanSchedules.findByTradesmanId(userId);
            }
            catch (NoSuchEntityException ignored) {
                tradesmanSchedule = TradesmanSchedule.of(userId, List.of(tradesmanScheduleCandidate.unavailableDate));
            }

            if (!DateRangeValidationEngine.getInstance().isValid(dateRange, tradesmanSchedule)) {
                throw new UserCategoryValidatorException(ExceptionsDictionary.TRADESMAN_ALREADY_TAKEN.getErrorCode(), ExceptionsDictionary.TRADESMAN_ALREADY_TAKEN.getMessage());
            }

            tradesmanSchedule.addUnavailableDate(tradesmanScheduleCandidate.unavailableDate);
            tradesmanSchedules.save(tradesmanSchedule);
        }

        projectTradesmenCandidates = ProjectTradesmenCandidate.of(projectId, tradesmenInformations);
        projectTradesmen = ProjectTradesmen.of(projectTradesmenId, projectTradesmenCandidates.projectId, projectTradesmenCandidates.tradesmenInformations, ProjectTradesmenState.CREATED);

        this.projectsTradesmen.save(projectTradesmen);
        this.eventBus.send(RegisteredProjectTradesmenRequirementsEvent.withProjectId(new ProjectTradesmenDTO(projectTradesmenId, projectTradesmen.getHistory())));
        System.out.println(this.projectsTradesmen.findAll());
        System.out.println(this.tradesmanSchedules.findAll());
        return projectTradesmenId;
    }
}
