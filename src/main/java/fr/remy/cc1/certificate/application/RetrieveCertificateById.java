package fr.remy.cc1.certificate.application;

import fr.remy.cc1.certificate.domain.CertificateId;
import fr.remy.cc1.kernel.Query;
import fr.remy.cc1.shared.domain.UserId;

public class RetrieveCertificateById implements Query {
    public final CertificateId certificateId;
    public final UserId userId;

    public RetrieveCertificateById(CertificateId certificateId, UserId userId) {
        this.certificateId = certificateId;
        this.userId = userId;
    }
}
