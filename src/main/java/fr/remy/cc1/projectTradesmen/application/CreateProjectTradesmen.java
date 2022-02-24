package fr.remy.cc1.projectTradesmen.application;

import fr.remy.cc1.kernel.Command;

import java.util.List;

public final class CreateProjectTradesmen implements Command {
    public final String projectId;
    public final List<String> tradesmenId;
    public final List<String> tradeJob;
    public final List<String> dailyRate;
    public final String currency;
    public final List<String> startDates;
    public final List<String> endDates;

    public CreateProjectTradesmen(String projectId, List<String> tradesmenId, List<String> tradeJob, List<String> dailyRate, String currency, List<String> startDates, List<String> endDates) {
        this.projectId = projectId;
        this.tradesmenId = tradesmenId;
        this.tradeJob = tradeJob;
        this.dailyRate = dailyRate;
        this.currency = currency;
        this.startDates = startDates;
        this.endDates = endDates;
    }
}
