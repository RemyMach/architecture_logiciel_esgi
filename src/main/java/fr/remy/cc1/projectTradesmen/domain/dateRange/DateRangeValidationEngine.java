package fr.remy.cc1.projectTradesmen.domain.dateRange;

import fr.remy.cc1.projectTradesmen.domain.scheduler.TradesmanSchedule;
import java.util.List;

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
        List<DateRange> unavailableDates = tradesmanSchedule.getUnavailableDates();
        if(unavailableDates.isEmpty()) {
            return true;
        }
        return unavailableDates.stream().noneMatch(unavailableDate -> overlaps(dateRange, unavailableDate));
    }

    private boolean overlaps(DateRange dateRange, DateRange unavailableDate) {
        return !(dateRange.getStartDate().before(unavailableDate.getStartDate()) && dateRange.getEndDate().before(unavailableDate.getStartDate())
                || dateRange.getStartDate().after(unavailableDate.getEndDate()) && dateRange.getEndDate().after(unavailableDate.getEndDate())
                || !(dateRange.getStartDate().equals(unavailableDate.getStartDate()) || dateRange.getEndDate().equals(unavailableDate.getEndDate())));
    }
}
