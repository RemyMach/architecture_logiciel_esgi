package fr.remy.cc1.application.certificate;

import fr.remy.cc1.domain.certificate.Certificate;
import fr.remy.cc1.domain.certificate.Certificates;
import fr.remy.cc1.kernel.QueryHandler;


public class RetrieveCertificateByIdHandler implements QueryHandler<RetrieveCertificateById, Certificate> {

    private final Certificates certificates;

    public RetrieveCertificateByIdHandler(Certificates certificates) {
        this.certificates = certificates;
    }

    @Override
    public Certificate handle(RetrieveCertificateById query) throws Exception {
        System.out.println(this.certificates.findAll());
        return this.certificates.byId(query.certificateId);
    }
}
