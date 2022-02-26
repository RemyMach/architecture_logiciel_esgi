package fr.remy.cc1.projectTradesmen.domain.scheduler;

import fr.remy.cc1.projectTradesmen.domain.dateRange.DateRange;

public final class TradesmanScheduleCandidate {
    public final DateRange unavailableDate;

    private TradesmanScheduleCandidate(DateRange unavailableDate) {
        this.unavailableDate = unavailableDate;
    }

    public static TradesmanScheduleCandidate of(DateRange unavailableDate) {
        return new TradesmanScheduleCandidate(unavailableDate);
    }
}
