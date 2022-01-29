package fr.remy.cc1.SpringConfiguration;

import fr.remy.cc1.application.certificate.RetrieveCertificateByIdHandler;
import fr.remy.cc1.domain.certificate.Certificate;
import fr.remy.cc1.domain.certificate.CertificateId;
import fr.remy.cc1.domain.certificate.Certificates;
import fr.remy.cc1.domain.user.UserId;
import fr.remy.cc1.infrastructure.certificates.InMemoryCertificates;
import fr.remy.cc1.infrastructure.exceptions.NoSuchEntityException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class CertificateConfiguration {

    @Bean
    public Certificates certificates() throws NoSuchEntityException {
        Certificates certificates =  new InMemoryCertificates();
        certificates.save(UserId.of(0), Certificate.of(certificates.nextIdentity(), "jean", "http://pomme"));
        return certificates;
    }

    @Bean
    public RetrieveCertificateByIdHandler retrieveCertificateByIdHandler() throws NoSuchEntityException {
        return new RetrieveCertificateByIdHandler(certificates());
    }
}
