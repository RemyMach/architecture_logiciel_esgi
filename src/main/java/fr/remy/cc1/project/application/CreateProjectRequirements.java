package fr.remy.cc1.project.application;

import fr.remy.cc1.kernel.Command;

import java.math.BigDecimal;
import java.util.List;

public class CreateProjectRequirements implements Command {

    public final int projectId;
    public final List<String> trade;
    public final List<String> skills;
    public final List<BigDecimal> amount;
    public final String currency;
    public final String address;
    public final List<Integer> duration;
    public final List<String> durationUnit;

    public CreateProjectRequirements(int projectId, List<String> trade, List<String> skills, List<BigDecimal> amount, String currency, String address, List<Integer> duration, List<String> durationUnit) {
        this.projectId = projectId;
        this.trade = trade;
        this.skills = skills;
        this.amount = amount;
        this.currency = currency;
        this.address = address;
        this.duration = duration;
        this.durationUnit = durationUnit;
    }
}
