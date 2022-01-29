package fr.remy.cc1.exposition.certificate;

import fr.remy.cc1.application.certificate.RetrieveCertificateById;
import fr.remy.cc1.application.certificate.RetrieveCertificateByIdHandler;
import fr.remy.cc1.domain.certificate.Certificate;
import fr.remy.cc1.domain.certificate.CertificateId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SuppressWarnings("unused")
@RestController
@RequestMapping("/certificates")
public class CertificateController {

    private final RetrieveCertificateByIdHandler retrieveCertificateByIdHandler;

    @Autowired
    public CertificateController(RetrieveCertificateByIdHandler retrieveCertificateByIdHandler) {
        this.retrieveCertificateByIdHandler = retrieveCertificateByIdHandler;
    }

    @GetMapping(path = "/{certificateId}",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CertificateResponse> getCertificateById(@PathVariable("certificateId") int certificateId) throws Exception {
        final Certificate certificate = this.retrieveCertificateByIdHandler.handle(new RetrieveCertificateById(CertificateId.of(certificateId)));
        CertificateResponse certificateResponse = new CertificateResponse(certificate.getCertificateId().getValue(), certificate.getName(), certificate.getLink());
        return ResponseEntity.ok(certificateResponse);
    }
}
