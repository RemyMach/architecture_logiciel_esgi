package fr.remy.cc1.project.application;

import fr.remy.cc1.infrastructure.exceptions.NoSuchEntityException;
import fr.remy.cc1.kernel.CommandHandler;
import fr.remy.cc1.kernel.error.ExceptionsDictionary;
import fr.remy.cc1.kernel.error.ValidationException;
import fr.remy.cc1.kernel.event.Event;
import fr.remy.cc1.kernel.event.EventBus;
import fr.remy.cc1.certificate.domain.skill.Skill;
import fr.remy.cc1.legacy.domain.trades.Trade;
import fr.remy.cc1.project.domain.duration.Duration;
import fr.remy.cc1.project.domain.duration.DurationUnit;
import fr.remy.cc1.project.domain.location.Address;
import fr.remy.cc1.project.domain.location.Location;
import fr.remy.cc1.project.domain.location.LocationGeocoding;
import fr.remy.cc1.project.domain.project.*;
import fr.remy.cc1.subscription.domain.Money;
import fr.remy.cc1.subscription.domain.currency.CurrencyCreator;

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
