package fr.remy.cc1.application.user;

import fr.remy.cc1.application.UserDTO;
import fr.remy.cc1.kernel.event.ApplicationEvent;
import fr.remy.cc1.kernel.event.Event;
import fr.remy.cc1.kernel.event.EventId;

import java.time.ZonedDateTime;

public class RegisteredUserEvent implements Event, ApplicationEvent {

    private final EventId eventId;
    private final ZonedDateTime occurredDate;
    private final UserDTO userDTO;

    private RegisteredUserEvent(EventId eventId, ZonedDateTime occurredDate, UserDTO userDTO) {
        this.eventId = eventId;
        this.occurredDate = occurredDate;
        this.userDTO = userDTO;
    }

    public static RegisteredUserEvent withUser(UserDTO userDTO) {
        return new RegisteredUserEvent(EventId.create(), ZonedDateTime.now(), userDTO);
    }

    @Override
    public EventId getId() {
        return eventId;
    }

    @Override
    public ZonedDateTime getOccurredDate() {
        return occurredDate;
    }

    public EventId getEventId() {
        return eventId;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }
}
