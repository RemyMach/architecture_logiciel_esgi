package fr.remy.cc1.projectTradesmen.domain.scheduler;

import fr.remy.cc1.projectTradesmen.domain.dateRange.DateRange;

import java.util.Objects;

public final class TradesmanScheduleCandidate {
    public final DateRange unavailableDate;

    private TradesmanScheduleCandidate(DateRange unavailableDate) {
        this.unavailableDate = Objects.requireNonNull(unavailableDate);
    }

    public static TradesmanScheduleCandidate of(DateRange unavailableDate) {
        return new TradesmanScheduleCandidate(unavailableDate);
    }
}
