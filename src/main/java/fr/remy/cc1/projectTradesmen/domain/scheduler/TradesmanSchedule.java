package fr.remy.cc1.projectTradesmen.domain.scheduler;

import fr.remy.cc1.projectTradesmen.domain.dateRange.DateRange;
import fr.remy.cc1.shared.domain.UserId;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class TradesmanSchedule {
    private final UserId tradesmanId;
    private List<DateRange> unavailableDates;

    private TradesmanSchedule(UserId tradesmanId) {
        this.tradesmanId = Objects.requireNonNull(tradesmanId);
        this.unavailableDates = new ArrayList<>();
    }

    public static TradesmanSchedule of(UserId tradesmanId) {
        return new TradesmanSchedule(tradesmanId);
    }

    public UserId getTradesmanId() {
        return tradesmanId;
    }

    public List<DateRange> getUnavailableDates() {
        return new ArrayList(unavailableDates);
    }

    public void addUnavailableDate(DateRange dateRange) {
        List<DateRange> newUnavailableDates = new ArrayList(unavailableDates);
        newUnavailableDates.add(dateRange);
        unavailableDates = newUnavailableDates;
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
