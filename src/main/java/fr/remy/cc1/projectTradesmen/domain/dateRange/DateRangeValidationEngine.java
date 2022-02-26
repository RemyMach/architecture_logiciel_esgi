package fr.remy.cc1.projectTradesmen.domain.dateRange;

import fr.remy.cc1.projectTradesmen.domain.scheduler.TradesmanSchedule;

public final class DateRangeValidationEngine {
    private static final DateRangeValidationEngine INSTANCE = new DateRangeValidationEngine();

    public static DateRangeValidationEngine getInstance() {
        return INSTANCE;
    }

    private DateRangeValidationEngine() {
    }

    public boolean isValid(DateRange dateRange) {
        return dateRange.getStartDate().before(dateRange.getEndDate());
    }

    public boolean isValid(DateRange dateRange, TradesmanSchedule tradesmanSchedule) {
        for (DateRange unavailableDate : tradesmanSchedule.getUnavailableDates()) {
            if (this.overlaps(dateRange, unavailableDate)) {
                return false;
            }
        }
        return true;
    }

    private boolean overlaps(DateRange dateRange, DateRange unavailableDate) {
        return (dateRange.getStartDate().before(unavailableDate.getStartDate()) && dateRange.getEndDate().before(unavailableDate.getStartDate()))
                || (dateRange.getStartDate().after(unavailableDate.getEndDate()) && dateRange.getEndDate().after(unavailableDate.getEndDate()));
    }
}
