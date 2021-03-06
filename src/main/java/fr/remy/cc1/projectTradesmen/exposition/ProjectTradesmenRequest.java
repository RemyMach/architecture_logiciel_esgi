package fr.remy.cc1.projectTradesmen.exposition;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public final class ProjectTradesmenRequest {
    @NotNull(message = "project_id_empty_null")
    @NotBlank(message = "project_id_empty_null")
    public String projectId;

    @NotNull(message = "tradesmen_id_empty_null")
    @NotEmpty(message = "tradesmen_id_empty_null")
    public List<String> tradesmenId;

    @NotNull(message = "trade_job_empty_null")
    @NotEmpty(message = "trade_job_empty_null")
    public List<String> tradeJob;

    @NotNull(message = "daily_rate_empty_null")
    @NotEmpty(message = "daily_rate_empty_null")
    public List<String> dailyRate;

    @NotNull(message = "currency_empty_null")
    @NotBlank(message = "currency_empty_null")
    public String currency;

    @NotNull(message = "start_dates_empty_null")
    @NotEmpty(message = "start_dates_empty_null")
    public List<String> startDates;

    @NotNull(message = "end_dates_empty_null")
    @NotEmpty(message = "end_dates_empty_null")
    public List<String> endDates;
}
