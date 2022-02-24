package fr.remy.cc1.projectTradesmen.domain.TrademenInformations;

import java.util.Date;
import java.util.Objects;

public final class DateRange {
    private final Date startDate;
    private final Date endDate;

    private DateRange(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static DateRange of(Date startDate, Date endDate) {
        return new DateRange(startDate, endDate);
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DateRange)) return false;
        DateRange dateRange = (DateRange) o;
        return Objects.equals(getStartDate(), dateRange.getStartDate()) && Objects.equals(getEndDate(), dateRange.getEndDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStartDate(), getEndDate());
    }

    @Override
    public String toString() {
        return "DateRange{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}