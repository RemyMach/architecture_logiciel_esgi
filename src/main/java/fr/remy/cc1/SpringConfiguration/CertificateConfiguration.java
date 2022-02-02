package fr.remy.cc1.SpringConfiguration;

import fr.remy.cc1.application.certificate.RetrieveCertificateByIdHandler;
import fr.remy.cc1.domain.certificate.Certificate;
import fr.remy.cc1.domain.certificate.CertificateId;
import fr.remy.cc1.domain.certificate.Certificates;
import fr.remy.cc1.domain.skill.Skill;
import fr.remy.cc1.domain.user.UserId;
import fr.remy.cc1.infrastructure.certificates.InMemoryCertificates;
import fr.remy.cc1.infrastructure.exceptions.NoSuchEntityException;
import fr.remy.cc1.kernel.error.ValidationException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.List;

@Configuration
@EnableScheduling
public class CertificateConfiguration {

    @Bean
    public Certificates certificates() throws NoSuchEntityException, ValidationException {
        Certificates certificates =  new InMemoryCertificates();
        certificates.save(UserId.of(0), Certificate.of(certificates.nextIdentity(), "jean", "http://pomme", List.of(Skill.of("cleaning"))));
        return certificates;
    }

    @Bean
    public RetrieveCertificateByIdHandler retrieveCertificateByIdHandler() throws NoSuchEntityException, ValidationException {
        return new RetrieveCertificateByIdHandler(certificates());
    }
}
