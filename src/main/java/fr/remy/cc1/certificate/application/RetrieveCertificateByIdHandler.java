package fr.remy.cc1.certificate.application;

import fr.remy.cc1.certificate.domain.Certificate;
import fr.remy.cc1.certificate.domain.Certificates;
import fr.remy.cc1.exposition.exception.DomainExceptionsDictionaryMapper;
import fr.remy.cc1.infrastructure.exceptions.InfrastructureExceptionsDictionary;
import fr.remy.cc1.infrastructure.exceptions.NoSuchEntityException;
import fr.remy.cc1.kernel.QueryHandler;

import java.util.List;


public class RetrieveCertificateByIdHandler implements QueryHandler<RetrieveCertificateById, Certificate> {

    private final Certificates certificates;

    public RetrieveCertificateByIdHandler(Certificates certificates) {
        this.certificates = certificates;
    }

    @Override
    public Certificate handle(RetrieveCertificateById query) throws Exception {

        List<Certificate> certificates =  this.certificates.byUserId(query.userId);
        Certificate certificate = certificates.stream().findFirst()
                .filter( certificateFromDb -> certificateFromDb.getCertificateId().getValue().equals(query.certificateId.getValue()))
                .orElseThrow(() -> new NoSuchEntityException(InfrastructureExceptionsDictionary.CERTIFICATE_NOT_FOUND.getErrorCode(), InfrastructureExceptionsDictionary.CERTIFICATE_NOT_FOUND.getMessage()));
        return certificate;
    }
}
