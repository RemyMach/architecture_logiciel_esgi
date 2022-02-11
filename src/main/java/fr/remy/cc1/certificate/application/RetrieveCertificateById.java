package fr.remy.cc1.certificate.application;

import fr.remy.cc1.certificate.domain.CertificateId;
import fr.remy.cc1.kernel.Query;

public class RetrieveCertificateById implements Query {
    public final CertificateId certificateId;

    public RetrieveCertificateById(CertificateId certificateId) {
        this.certificateId = certificateId;
    }
}
