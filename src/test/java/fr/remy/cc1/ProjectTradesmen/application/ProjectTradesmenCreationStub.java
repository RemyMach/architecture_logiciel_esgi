package fr.remy.cc1.ProjectTradesmen.application;

import fr.remy.cc1.kernel.event.Subscriber;
import fr.remy.cc1.member.application.RegisteredContractorEvent;
import fr.remy.cc1.member.application.RegisteredContractorEventMessengerSubscription;
import fr.remy.cc1.member.application.RegisteredTradesmanEvent;
import fr.remy.cc1.member.application.RegisteredTradesmanEventMessengerSubscription;
import fr.remy.cc1.projectTradesmen.application.ActivateProjectTradesmenEventMessengerSubscription;
import fr.remy.cc1.projectTradesmen.application.RegisteredProjectTradesmenRequirementsEvent;
import fr.remy.cc1.projectTradesmen.domain.ProjectTradesmen;
import fr.remy.cc1.projectTradesmen.domain.ProjectsTradesmen;
import fr.remy.cc1.projectTradesmen.infrastructure.ProjectTradesmenCreationEventBus;
import fr.remy.cc1.subscription.application.SubscriptionPaymentFailedEvent;
import fr.remy.cc1.subscription.application.SubscriptionSuccessTerminatedEvent;

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
