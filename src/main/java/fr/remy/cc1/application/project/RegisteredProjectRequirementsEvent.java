package fr.remy.cc1.application.project;

import fr.remy.cc1.application.ProjectDTO;
import fr.remy.cc1.kernel.event.ApplicationEvent;
import fr.remy.cc1.kernel.event.Event;
import fr.remy.cc1.kernel.event.EventId;

import java.time.ZonedDateTime;

public final class RegisteredProjectRequirementsEvent implements Event, ApplicationEvent {

    private final EventId eventId;
    private final ZonedDateTime occurredDate;
    private final ProjectDTO projectDTO;

    private RegisteredProjectRequirementsEvent(EventId eventId, ZonedDateTime occurredDate, ProjectDTO projectDTO) {
        this.eventId = eventId;
        this.occurredDate = occurredDate;
        this.projectDTO = projectDTO;
    }

    public static RegisteredProjectRequirementsEvent withProjectId(ProjectDTO projectDTO) {
        return new RegisteredProjectRequirementsEvent(EventId.create(), ZonedDateTime.now(), projectDTO);
    }

    @Override
    public EventId getId() {
        return this.eventId;
    }

    @Override
    public ZonedDateTime getOccurredDate() {
        return this.occurredDate;
    }

    public ProjectDTO getProjectIdDTO() {
        return this.projectDTO;
    }
}
