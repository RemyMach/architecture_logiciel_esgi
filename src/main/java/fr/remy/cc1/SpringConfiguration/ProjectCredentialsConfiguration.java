package fr.remy.cc1.SpringConfiguration;

import fr.remy.cc1.application.CreateProjectCredentialsCommandHandler;
import fr.remy.cc1.application.project.ActivateProjectEventMessengerSubscription;
import fr.remy.cc1.application.project.RegisteredProjectCredentialsEvent;
import fr.remy.cc1.domain.location.LocationGeocoding;
import fr.remy.cc1.domain.project.Projects;
import fr.remy.cc1.domain.project.ProjectsCredentials;
import fr.remy.cc1.infrastructure.location.InMemoryGeocoding;
import fr.remy.cc1.infrastructure.project.InMemoryProjectsCredentials;
import fr.remy.cc1.infrastructure.project.ProjectCredentialsCreationEventBus;
import fr.remy.cc1.kernel.event.Event;
import fr.remy.cc1.kernel.event.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.List;

@Configuration
@EnableScheduling
public class ProjectCredentialsConfiguration {

    @Autowired
    Projects projects;

    @Bean
    public ProjectsCredentials projectsCredentials() {
        return new InMemoryProjectsCredentials();
    }

    @Bean
    public LocationGeocoding locationGeocoding() {
        return new InMemoryGeocoding();
    }

    @Bean
    public EventBus<Event> projectCredentialsCreationEventBus() {
        ProjectCredentialsCreationEventBus.getInstance().registerSubscriber(RegisteredProjectCredentialsEvent.class, List.of(new ActivateProjectEventMessengerSubscription()));
        return ProjectCredentialsCreationEventBus.getInstance();
    }

    @Bean
    public CreateProjectCredentialsCommandHandler createProjectCredentialsCommandHandler() {
        return new CreateProjectCredentialsCommandHandler(projects, projectsCredentials(), locationGeocoding(), projectCredentialsCreationEventBus());
    }
}
