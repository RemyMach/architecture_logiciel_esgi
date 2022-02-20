package fr.remy.cc1.projectTradesmen.application;

import fr.remy.cc1.kernel.event.ApplicationEvent;
import fr.remy.cc1.kernel.event.Event;
import fr.remy.cc1.kernel.event.EventId;

import java.time.ZonedDateTime;

public class RegisteredProjectTradesmenRequirementsEvent implements Event, ApplicationEvent {
    private final EventId eventId;
    private final ZonedDateTime occurredDate;
    private final ProjectTradesmenDTO projectTradesmenDTO;

    private RegisteredProjectTradesmenRequirementsEvent(EventId eventId, ZonedDateTime occurredDate, ProjectTradesmenDTO projectTradesmenDTO) {
        this.eventId = eventId;
        this.occurredDate = occurredDate;
        this.projectTradesmenDTO = projectTradesmenDTO;
    }

    public static RegisteredProjectTradesmenRequirementsEvent withProjectId(ProjectTradesmenDTO projectTradesmenDTO) {
        return new RegisteredProjectTradesmenRequirementsEvent(EventId.create(), ZonedDateTime.now(), projectTradesmenDTO);
    }

    @Override
    public EventId getId() {
        return this.eventId;
    }

    @Override
    public ZonedDateTime getOccurredDate() {
        return this.occurredDate;
    }

    public ProjectTradesmenDTO getProjectTradesmenDTO() {
        return this.projectTradesmenDTO;
    }
}
