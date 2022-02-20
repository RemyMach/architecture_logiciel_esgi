package fr.remy.cc1.projectTradesmen.application;

import fr.remy.cc1.kernel.event.Subscriber;
import fr.remy.cc1.projectTradesmen.domain.ProjectTradesmenState;

public class ActivateProjectTradesmenEventMessengerSubscription implements Subscriber<RegisteredProjectTradesmenRequirementsEvent>
{
    public ActivateProjectTradesmenEventMessengerSubscription() {}

    @Override
    public void accept(RegisteredProjectTradesmenRequirementsEvent event) {
        ProjectTradesmenDTO projectTradesmenDTO = event.getProjectTradesmenDTO();
        projectTradesmenDTO.getHistory().append(ProjectTradesmenState.ACTIVE);
    }
}
