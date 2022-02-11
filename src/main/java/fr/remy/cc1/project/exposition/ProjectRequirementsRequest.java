package fr.remy.cc1.project.exposition;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ProjectRequirementsRequest {

    @NotNull(message = "project_empty_null")
    public int projectId;

    @NotNull(message = "trades_empty_null")
    @NotBlank(message = "trades_empty_null")
    public String trade;

    @NotNull(message = "skills_empty_null")
    @NotBlank(message = "skills_empty_null")
    public String skills;

    @NotNull(message = "amount_empty_null")
    @DecimalMin("0")
    public BigDecimal amount;

    @NotNull(message = "currency_empty_null")
    @NotBlank(message = "currency_empty_null")
    public String currency;

    @NotNull(message = "address_empty_null")
    @NotBlank(message = "address_empty_null")
    public String address;

    @NotNull(message = "duration_empty_null")
    @DecimalMin("0")
    public int duration;

    @NotNull(message = "durationUnit_empty_null")
    @NotBlank(message = "durationUnit_empty_null")
    public String durationUnit;
}
