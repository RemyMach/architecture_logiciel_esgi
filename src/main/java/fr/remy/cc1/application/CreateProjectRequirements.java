package fr.remy.cc1.application;

import fr.remy.cc1.kernel.Command;

import java.math.BigDecimal;

public class CreateProjectRequirements implements Command {

    public final int projectId;
    public final String trade;
    public final String skills;
    public final BigDecimal amount;
    public final String currency;
    public final String address;
    public final int duration;
    public final String durationUnit;

    public CreateProjectRequirements(int projectId, String trade, String skills, BigDecimal amount, String currency, String address, int duration, String durationUnit) {
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
