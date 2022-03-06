package fr.remy.cc1.SpringConfiguration;

import fr.remy.cc1.kernel.event.Event;
import fr.remy.cc1.kernel.event.EventBus;
import fr.remy.cc1.member.domain.user.Users;
import fr.remy.cc1.project.domain.project.Projects;
import fr.remy.cc1.projectTradesmen.application.ActivateProjectTradesmenEventMessengerSubscription;
import fr.remy.cc1.projectTradesmen.application.CreateProjectTradesmenCommandHandler;
import fr.remy.cc1.projectTradesmen.application.RegisteredProjectTradesmenRequirementsEvent;
import fr.remy.cc1.projectTradesmen.domain.ProjectsTradesmen;
import fr.remy.cc1.projectTradesmen.domain.scheduler.TradesmanSchedules;
import fr.remy.cc1.projectTradesmen.infrastructure.InMemoryProjectTradesmen;
import fr.remy.cc1.projectTradesmen.infrastructure.InMemoryTradesmanSchedule;
import fr.remy.cc1.projectTradesmen.infrastructure.ProjectTradesmenCreationEventBus;
import fr.remy.cc1.shared.infrastructure.InMemory.TradesmanSchedulesData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
@EnableScheduling
public class ProjectTradesmenConfiguration
{
    @Bean
    public ProjectsTradesmen projectsTradesmen()
    {
        return new InMemoryProjectTradesmen();
    }

    @Bean
    public TradesmanSchedulesData tradesmanSchedulesData() {
        TradesmanSchedulesData.setup(new ConcurrentHashMap<>());
        return TradesmanSchedulesData.getInstance();
    }

    @Bean
    public TradesmanSchedules tradesmanSchedule()
    {
        return new InMemoryTradesmanSchedule(tradesmanSchedulesData().data);
    }

    @Bean
    public EventBus<Event> projectTradesmenCreationEventBus() {
        ProjectTradesmenCreationEventBus.getInstance().registerSubscriber(RegisteredProjectTradesmenRequirementsEvent.class, List.of(new ActivateProjectTradesmenEventMessengerSubscription()));
        return ProjectTradesmenCreationEventBus.getInstance();
    }

    @Autowired
    @Bean
    public CreateProjectTradesmenCommandHandler createProjectTradesmenCommandHandler(Projects projects, Users users)
    {
        return new CreateProjectTradesmenCommandHandler(projectsTradesmen(), tradesmanSchedule(), projects, users, projectTradesmenCreationEventBus());
    }
}
