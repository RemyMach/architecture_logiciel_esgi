package fr.remy.cc1.SpringConfiguration;

import fr.remy.cc1.kernel.event.Event;
import fr.remy.cc1.kernel.event.EventBus;
import fr.remy.cc1.member.domain.user.Users;
import fr.remy.cc1.project.domain.project.Projects;
import fr.remy.cc1.projectTradesmen.application.ActivateProjectTradesmenEventMessengerSubscription;
import fr.remy.cc1.projectTradesmen.application.CreateProjectTradesmenCommandHandler;
import fr.remy.cc1.projectTradesmen.application.RegisteredProjectTradesmenRequirementsEvent;
import fr.remy.cc1.projectTradesmen.domain.ProjectsTradesmen;
import fr.remy.cc1.projectTradesmen.infrastructure.InMemoryProjectTradesmen;
import fr.remy.cc1.projectTradesmen.infrastructure.ProjectTradesmenCreationEventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.List;

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
    public EventBus<Event> projectTradesmenCreationEventBus() {
        ProjectTradesmenCreationEventBus.getInstance().registerSubscriber(RegisteredProjectTradesmenRequirementsEvent.class, List.of(new ActivateProjectTradesmenEventMessengerSubscription()));
        return ProjectTradesmenCreationEventBus.getInstance();
    }

    @Autowired
    @Bean
    public CreateProjectTradesmenCommandHandler createProjectTradesmenCommandHandler(Projects projects, Users users)
    {
        return new CreateProjectTradesmenCommandHandler(projectsTradesmen(), projects, users, projectTradesmenCreationEventBus());
    }
}
