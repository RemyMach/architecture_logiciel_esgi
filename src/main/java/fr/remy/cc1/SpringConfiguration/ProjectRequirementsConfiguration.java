package fr.remy.cc1.SpringConfiguration;

import fr.remy.cc1.application.CreateProjectRequirementsCommandHandler;
import fr.remy.cc1.application.project.ActivateProjectEventMessengerSubscription;
import fr.remy.cc1.application.project.RegisteredProjectRequirementsEvent;
import fr.remy.cc1.domain.location.LatLng;
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

import java.math.BigDecimal;
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
        InMemoryGeocoding locationGeocoding = new InMemoryGeocoding();
        locationGeocoding.addLocation("Paris", LatLng.of(BigDecimal.valueOf(48.864716), BigDecimal.valueOf(2.349014)));
        locationGeocoding.addLocation("Toulon", LatLng.of(BigDecimal.valueOf(43.125832), BigDecimal.valueOf(5.930556)));
        locationGeocoding.addLocation("Lille", LatLng.of(BigDecimal.valueOf(50.629250), BigDecimal.valueOf(3.057256)));
        locationGeocoding.addLocation("Lyon", LatLng.of(BigDecimal.valueOf(45.763420), BigDecimal.valueOf(4.834277)));
        locationGeocoding.addLocation("Grenoble", LatLng.of(BigDecimal.valueOf(45.171547), BigDecimal.valueOf(5.722387)));
        locationGeocoding.addLocation("Reims", LatLng.of(BigDecimal.valueOf(49.262798), BigDecimal.valueOf(4.034700)));
        locationGeocoding.addLocation("Nantes", LatLng.of(BigDecimal.valueOf(47.218102), BigDecimal.valueOf(-1.552800)));
        locationGeocoding.addLocation("Toulouse", LatLng.of(BigDecimal.valueOf(43.604500), BigDecimal.valueOf(1.444000)));
        locationGeocoding.addLocation("Aix-en-Provence", LatLng.of(BigDecimal.valueOf(43.526302), BigDecimal.valueOf(5.445429)));
        return locationGeocoding;
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
