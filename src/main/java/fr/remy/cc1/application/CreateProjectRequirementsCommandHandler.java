package fr.remy.cc1.application;

import fr.remy.cc1.application.project.RegisteredProjectRequirementsEvent;
import fr.remy.cc1.domain.duration.Duration;
import fr.remy.cc1.domain.duration.DurationUnit;
import fr.remy.cc1.domain.location.Address;
import fr.remy.cc1.domain.location.Location;
import fr.remy.cc1.domain.location.LocationGeocoding;
import fr.remy.cc1.domain.payment.Money;
import fr.remy.cc1.domain.payment.currency.CurrencyCreator;
import fr.remy.cc1.domain.project.*;
import fr.remy.cc1.domain.skill.Skill;
import fr.remy.cc1.domain.trades.Trade;
import fr.remy.cc1.infrastructure.exceptions.NoSuchEntityException;
import fr.remy.cc1.kernel.CommandHandler;
import fr.remy.cc1.kernel.error.ExceptionsDictionary;
import fr.remy.cc1.kernel.error.ValidationException;
import fr.remy.cc1.kernel.event.Event;
import fr.remy.cc1.kernel.event.EventBus;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class CreateProjectRequirementsCommandHandler implements CommandHandler<CreateProjectRequirements, ProjectId> {

    private final Projects projects;
    private final ProjectsRequirements projectsRequirements;
    private final LocationGeocoding locationGeocoding;
    private final EventBus<Event> eventBus;


    public CreateProjectRequirementsCommandHandler(Projects projects, ProjectsRequirements projectsRequirements, LocationGeocoding locationGeocoding, EventBus<Event> eventBus) {
        this.projects = projects;
        this.projectsRequirements = projectsRequirements;
        this.locationGeocoding = locationGeocoding;
        this.eventBus = eventBus;
    }

    @Override
    public ProjectId handle(CreateProjectRequirements createProjectRequirements) throws ValidationException, NoSuchEntityException {
        ProjectId projectId = ProjectId.of(createProjectRequirements.projectId);
        Project project = this.projects.byId(projectId);

        ProjectRequirements projectRequirements = this.projectsRequirements.byId(projectId);
        if (projectRequirements != null) {
            throw new ValidationException(ExceptionsDictionary.PROJECT_REQUIREMENTS_ALREADY_EXISTS.getErrorCode(), ExceptionsDictionary.PROJECT_REQUIREMENTS_ALREADY_EXISTS.getMessage());
        }

        ProjectRequirementsCandidate projectRequirementsCandidate = ProjectRequirementsCandidate.of(
                List.copyOf(Arrays.stream(createProjectRequirements.trade.split(",")).map(Trade::of).collect(Collectors.toList())),
                List.copyOf(Arrays.stream(createProjectRequirements.skills.split(",")).map(Skill::of).collect(Collectors.toList())),
                Money.of(createProjectRequirements.amount, CurrencyCreator.getValueOf(createProjectRequirements.currency)),
                Location.of(Address.of(createProjectRequirements.address), locationGeocoding.processAddress(Address.of(createProjectRequirements.address))),
                Duration.of(createProjectRequirements.duration, DurationUnit.getUnitFromCode(createProjectRequirements.durationUnit)));
        projectRequirements = ProjectRequirements.of(projectId, projectRequirementsCandidate.tradeList, projectRequirementsCandidate.skills, projectRequirementsCandidate.budget, projectRequirementsCandidate.location, projectRequirementsCandidate.duration);
        this.projectsRequirements.save(projectRequirements);
        this.eventBus.send(RegisteredProjectRequirementsEvent.withProjectId(new ProjectDTO(projectId, project.getHistory())));
        System.out.println(this.projectsRequirements.findAll());
        return projectId;
    }
}
