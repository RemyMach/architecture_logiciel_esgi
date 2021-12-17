package fr.remy.cc1.kernel.event;

import java.time.ZonedDateTime;

public interface Event {

    EventId getId();

    ZonedDateTime getOccurredDate();
}
