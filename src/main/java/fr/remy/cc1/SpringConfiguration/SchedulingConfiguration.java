package fr.remy.cc1.SpringConfiguration;

import fr.remy.cc1.application.scheduler.PaymentScheduler;
import fr.remy.cc1.domain.user.Users;
import fr.remy.cc1.infrastructure.user.InMemoryUsers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class SchedulingConfiguration {

    @Bean
    public Users users() {
        return new InMemoryUsers();
    }

    @Bean
    PaymentScheduler paymentScheduler() {
        return new PaymentScheduler(users());
    }
}
