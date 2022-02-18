package fr.remy.cc1.projectTradesmen.application;

import fr.remy.cc1.domain.UserId;
import fr.remy.cc1.kernel.Command;
import fr.remy.cc1.project.domain.project.ProjectId;

import java.util.List;

public final class CreateProjectTradesmen implements Command
{
    public final String projectId;
    public final String tradesmenId;

    public CreateProjectTradesmen(String projectId, String tradesmenId) {
        this.projectId = projectId;
        this.tradesmenId = tradesmenId;
    }
}
