package fr.remy.cc1.ProjectTradesmen.application;

import fr.remy.cc1.kernel.event.Subscriber;
import fr.remy.cc1.projectTradesmen.application.ActivateProjectTradesmenEventMessengerSubscription;
import fr.remy.cc1.projectTradesmen.application.RegisteredProjectTradesmenRequirementsEvent;
import fr.remy.cc1.projectTradesmen.infrastructure.ProjectTradesmenCreationEventBus;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ProjectTradesmenCreationStub {
    public static void initProjectTradesmenCreationTest() {
        buildStubEventBus();
    }

    private static void buildStubEventBus() {
        Map<Class, List<Subscriber>> subscriptionMap = Map.of(
                RegisteredProjectTradesmenRequirementsEvent.class, Collections.singletonList(new ActivateProjectTradesmenEventMessengerSubscription())
        );

        ProjectTradesmenCreationEventBus projectTradesmenCreationEventBus = ProjectTradesmenCreationEventBus.getInstance();
        projectTradesmenCreationEventBus.setSubscribers(subscriptionMap);
    }
}
