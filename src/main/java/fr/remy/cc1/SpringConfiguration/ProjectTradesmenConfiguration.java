package fr.remy.cc1.SpringConfiguration;

import fr.remy.cc1.projectTradesmen.application.CreateProjectTradesmenCommandHandler;
import fr.remy.cc1.projectTradesmen.domain.ProjectsTradesmen;
import fr.remy.cc1.projectTradesmen.infrastructure.InMemoryProjectTradesmen;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

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
    public CreateProjectTradesmenCommandHandler createProjectTradesmenCommandHandler()
    {
        return new CreateProjectTradesmenCommandHandler(projectsTradesmen());
    }
}
