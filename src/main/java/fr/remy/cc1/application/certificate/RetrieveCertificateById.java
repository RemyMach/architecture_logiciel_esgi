package fr.remy.cc1.application.certificate;

import fr.remy.cc1.domain.certificate.CertificateId;
import fr.remy.cc1.domain.user.UserCategory;
import fr.remy.cc1.kernel.Query;

public class RetrieveCertificateById implements Query {
    public final CertificateId certificateId;

    public RetrieveCertificateById(CertificateId certificateId) {
        this.certificateId = certificateId;
    }
}
