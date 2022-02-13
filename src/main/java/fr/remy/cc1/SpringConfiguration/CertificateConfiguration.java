package fr.remy.cc1.SpringConfiguration;

import fr.remy.cc1.certificate.application.RetrieveCertificateByIdHandler;
import fr.remy.cc1.certificate.domain.Certificate;
import fr.remy.cc1.certificate.domain.Certificates;
import fr.remy.cc1.domain.skill.Skill;
import fr.remy.cc1.certificate.infrastructure.InMemoryCertificates;
import fr.remy.cc1.domain.UserId;
import fr.remy.cc1.kernel.error.ValidationException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.List;

@Configuration
@EnableScheduling
public class CertificateConfiguration {

    @Bean
    public Certificates certificates() throws ValidationException {
        Certificates certificates =  new InMemoryCertificates();
        certificates.save(UserId.of(0), Certificate.of(certificates.nextIdentity(), "jean", "http://pomme", List.of(Skill.of("cleaning"))));
        return certificates;
    }

    @Bean
    public RetrieveCertificateByIdHandler retrieveCertificateByIdHandler() throws ValidationException {
        return new RetrieveCertificateByIdHandler(certificates());
    }
}
