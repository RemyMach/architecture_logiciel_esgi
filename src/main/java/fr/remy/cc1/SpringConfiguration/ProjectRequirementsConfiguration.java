package fr.remy.cc1.SpringConfiguration;

import fr.remy.cc1.application.CreateProjectRequirementsCommandHandler;
import fr.remy.cc1.application.project.ActivateProjectEventMessengerSubscription;
import fr.remy.cc1.application.project.RegisteredProjectRequirementsEvent;
import fr.remy.cc1.domain.location.LocationGeocoding;
import fr.remy.cc1.domain.project.Projects;
import fr.remy.cc1.domain.project.ProjectsRequirements;
import fr.remy.cc1.infrastructure.location.InMemoryGeocoding;
import fr.remy.cc1.infrastructure.project.InMemoryProjectsRequirements;
import fr.remy.cc1.infrastructure.project.ProjectRequirementsCreationEventBus;
import fr.remy.cc1.kernel.event.Event;
import fr.remy.cc1.kernel.event.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.List;

@Configuration
@EnableScheduling
public class ProjectRequirementsConfiguration {

    @Autowired
    Projects projects;

    @Bean
    public ProjectsRequirements projectsRequirements() {
        return new InMemoryProjectsRequirements();
    }

    @Bean
    public LocationGeocoding locationGeocoding() {
        return new InMemoryGeocoding();
    }

    @Bean
    public EventBus<Event> projectRequirementsCreationEventBus() {
        ProjectRequirementsCreationEventBus.getInstance().registerSubscriber(RegisteredProjectRequirementsEvent.class, List.of(new ActivateProjectEventMessengerSubscription()));
        return ProjectRequirementsCreationEventBus.getInstance();
    }

    @Bean
    public CreateProjectRequirementsCommandHandler createProjectRequirementsCommandHandler() {
        return new CreateProjectRequirementsCommandHandler(projects, projectsRequirements(), locationGeocoding(), projectRequirementsCreationEventBus());
    }
}
