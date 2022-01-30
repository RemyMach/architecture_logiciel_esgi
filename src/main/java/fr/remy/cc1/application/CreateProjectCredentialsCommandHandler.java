package fr.remy.cc1.application;

import fr.remy.cc1.application.project.RegisteredProjectCredentialsEvent;
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

public final class CreateProjectCredentialsCommandHandler implements CommandHandler<CreateProjectCredentials, ProjectId> {

    private final Projects projects;
    private final ProjectsCredentials projectsCredentials;
    private final LocationGeocoding locationGeocoding;
    private final EventBus<Event> eventBus;


    public CreateProjectCredentialsCommandHandler(Projects projects, ProjectsCredentials projectsCredentials, LocationGeocoding locationGeocoding, EventBus<Event> eventBus) {
        this.projects = projects;
        this.projectsCredentials = projectsCredentials;
        this.locationGeocoding = locationGeocoding;
        this.eventBus = eventBus;
    }

    @Override
    public ProjectId handle(CreateProjectCredentials createProjectCredentials) throws ValidationException {
        ProjectId projectId = ProjectId.of(createProjectCredentials.projectId);
        Project project;

        try {
            project = this.projects.byId(projectId);
        } catch (NoSuchEntityException e) {
            throw new ValidationException(ExceptionsDictionary.INVALID_PROJECT_ID.getErrorCode(), ExceptionsDictionary.INVALID_PROJECT_ID.getMessage());
        }

        try {
            ProjectCredentials projectCredentials = this.projectsCredentials.byId(projectId);
            if (projectCredentials != null) {
                throw new ValidationException(ExceptionsDictionary.PROJECT_CREDENTIALS_ALREADY_EXISTS.getErrorCode(), ExceptionsDictionary.PROJECT_CREDENTIALS_ALREADY_EXISTS.getMessage());
            }
        } catch (NoSuchEntityException ignored) {
        }

        ProjectCredentialsCandidate projectCredentialsCandidate = ProjectCredentialsCandidate.of(
                List.copyOf(Arrays.stream(createProjectCredentials.trade.split(",")).map(Trade::of).collect(Collectors.toList())),
                List.copyOf(Arrays.stream(createProjectCredentials.skills.split(",")).map(Skill::of).collect(Collectors.toList())),
                Money.of(createProjectCredentials.amount, CurrencyCreator.getValueOf(createProjectCredentials.currency)),
                Location.of(Address.of(createProjectCredentials.address), locationGeocoding.processAddress(Address.of(createProjectCredentials.address))),
                Duration.of(createProjectCredentials.duration, DurationUnit.getUnitFromCode(createProjectCredentials.durationUnit)));
        ProjectCredentials projectCredentials = ProjectCredentials.of(projectId, projectCredentialsCandidate.tradeList, projectCredentialsCandidate.skills, projectCredentialsCandidate.budget, projectCredentialsCandidate.location, projectCredentialsCandidate.duration);
        this.projectsCredentials.save(projectCredentials);
        this.eventBus.send(RegisteredProjectCredentialsEvent.withProjectId(new ProjectDTO(projectId, project.getHistory())));
        System.out.println(this.projectsCredentials.findAll());
        return projectId;
    }
}
