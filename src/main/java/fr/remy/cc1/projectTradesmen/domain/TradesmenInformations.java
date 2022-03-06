package fr.remy.cc1.projectTradesmen.domain;

import fr.remy.cc1.projectTradesmen.domain.dateRange.DateRange;
import fr.remy.cc1.shared.domain.UserId;
import fr.remy.cc1.shared.domain.money.Money;
import fr.remy.cc1.shared.domain.trade.TradeJobs;

import java.util.Objects;

public final class TradesmenInformations {
    private final UserId tradesmenId;
    private final TradeJobs tradeJob;
    private final Money dailyRate;
    private final DateRange dateRange;

    private TradesmenInformations(UserId tradesmenId, TradeJobs tradeJob, Money dailyRate, DateRange dateRange) {
        this.tradesmenId = Objects.requireNonNull(tradesmenId);
        this.tradeJob = Objects.requireNonNull(tradeJob);
        this.dailyRate = Objects.requireNonNull(dailyRate);
        this.dateRange = Objects.requireNonNull(dateRange);
    }

    public static TradesmenInformations of(UserId tradesmenId, TradeJobs tradeJob, Money dailyRate, DateRange dateRange) {
        return new TradesmenInformations(tradesmenId, tradeJob, dailyRate, dateRange);
    }

    public UserId getTradesmenId() {
        return tradesmenId;
    }

    public TradeJobs getTradeJob() {
        return tradeJob;
    }

    public Money getDailyRate() {
        return dailyRate;
    }

    public DateRange getDateRange() {
        return dateRange;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TradesmenInformations)) return false;
        TradesmenInformations that = (TradesmenInformations) o;
        return Objects.equals(getTradesmenId(), that.getTradesmenId()) && getTradeJob() == that.getTradeJob() && Objects.equals(getDailyRate(), that.getDailyRate()) && Objects.equals(getDateRange(), that.getDateRange());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTradesmenId(), getTradeJob(), getDailyRate(), getDateRange());
    }

    @Override
    public String toString() {
        return "TradesmenInformations{" +
                "tradesmenId=" + tradesmenId +
                ", tradeJob=" + tradeJob +
                ", dailyRate=" + dailyRate +
                ", dateRange=" + dateRange +
                '}';
    }
}
