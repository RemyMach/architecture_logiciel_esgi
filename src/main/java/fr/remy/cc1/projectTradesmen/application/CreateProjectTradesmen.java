package fr.remy.cc1.projectTradesmen.application;

import fr.remy.cc1.kernel.Command;

import java.util.List;

public final class CreateProjectTradesmen implements Command {
    public final String projectId;
    public final List<String> tradesmenId;

    public CreateProjectTradesmen(String projectId, List<String> tradesmenId) {
        this.projectId = projectId;
        this.tradesmenId = tradesmenId;
    }
}
