package fr.remy.cc1.exposition.project;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

public class ProjectRequirementsRequest {

    @NotNull(message = "project_empty_null")
    public int projectId;

    @NotNull(message = "trades_empty_null")
    @NotEmpty(message = "trades_empty_null")
    public List<String> trade;

    @NotNull(message = "skills_empty_null")
    @NotEmpty(message = "skills_empty_null")
    public List<String> skills;

    @NotNull(message = "amount_empty_null")
    @NotEmpty(message = "amount_empty_null")
    public List<BigDecimal> amount;

    @NotNull(message = "currency_empty_null")
    @NotBlank(message = "currency_empty_null")
    public String currency;

    @NotNull(message = "address_empty_null")
    @NotBlank(message = "address_empty_null")
    public String address;

    @NotNull(message = "duration_empty_null")
    @NotEmpty(message = "duration_empty_null")
    public List<Integer> duration;

    @NotNull(message = "durationUnit_empty_null")
    @NotEmpty(message = "durationUnit_empty_null")
    public List<String> durationUnit;
}
