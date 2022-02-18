package fr.remy.cc1.projectTradesmen.exposition;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public final class ProjectTradesmenRequest {
    @NotNull(message = "project_id_empty_null")
    @NotBlank(message = "project_id_empty_null")
    public String projectId;

    @NotNull(message = "tradesmen_id_empty_null")
    @NotBlank(message = "tradesmen_id_empty_null")
    public String tradesmenId;
}
