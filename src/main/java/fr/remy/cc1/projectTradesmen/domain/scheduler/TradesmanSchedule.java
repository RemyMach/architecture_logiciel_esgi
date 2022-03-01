package fr.remy.cc1.projectTradesmen.domain.scheduler;

import fr.remy.cc1.domain.UserId;
import fr.remy.cc1.projectTradesmen.domain.dateRange.DateRange;

import java.util.List;
import java.util.Objects;

public final class TradesmanSchedule {
    private final UserId tradesmanId;
    private final List<DateRange> unavailableDates;

    private TradesmanSchedule(UserId tradesmanId, List<DateRange> unavailableDates) {
        this.tradesmanId = Objects.requireNonNull(tradesmanId);
        this.unavailableDates = Objects.requireNonNull(unavailableDates);
    }

    public static TradesmanSchedule of(UserId tradesmanId, List<DateRange> unavailableDates) {
        return new TradesmanSchedule(tradesmanId, unavailableDates);
    }

    public UserId getTradesmanId() {
        return tradesmanId;
    }

    public List<DateRange> getUnavailableDates() {
        return unavailableDates;
    }

    public void addUnavailableDate(DateRange dateRange) {
        unavailableDates.add(dateRange);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TradesmanSchedule)) return false;
        TradesmanSchedule that = (TradesmanSchedule) o;
        return Objects.equals(getTradesmanId(), that.getTradesmanId()) && Objects.equals(getUnavailableDates(), that.getUnavailableDates());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTradesmanId(), getUnavailableDates());
    }

    @Override
    public String toString() {
        return "TradesmanSchedule{" +
                "tradesmanId=" + tradesmanId +
                ", unavailableDates=" + unavailableDates +
                '}';
    }
}
