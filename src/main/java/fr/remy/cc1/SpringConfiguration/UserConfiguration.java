package fr.remy.cc1.SpringConfiguration;

import fr.remy.cc1.domain.user.Users;
import fr.remy.cc1.infrastructure.user.InMemoryUsers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfiguration {

    @Bean
    public Users users() {
        return new InMemoryUsers();
    }

}
