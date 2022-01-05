package fr.remy.cc1.SpringConfiguration;

import fr.remy.cc1.exposition.scheduler.PaymentScheduler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class SchedulingConfiguration {

    @Bean
    PaymentScheduler paymentScheduler() {
        return new PaymentScheduler();
    }
}
