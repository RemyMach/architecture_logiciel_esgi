package fr.remy.cc1.SpringConfiguration;

import fr.remy.cc1.project.application.CreateProjectCommandHandler;
import fr.remy.cc1.project.domain.project.Projects;
import fr.remy.cc1.infrastructure.project.InMemoryProjects;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class ProjectConfiguration {

    @Bean
    public Projects projects() {
        return new InMemoryProjects();
    }

    @Bean
    public CreateProjectCommandHandler createProjectCommandHandler() {
        return new CreateProjectCommandHandler(projects());
    }
}
