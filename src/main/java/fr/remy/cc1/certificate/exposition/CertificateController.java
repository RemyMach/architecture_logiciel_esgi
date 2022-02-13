package fr.remy.cc1.certificate.exposition;

import fr.remy.cc1.certificate.application.RetrieveCertificateById;
import fr.remy.cc1.certificate.application.RetrieveCertificateByIdHandler;
import fr.remy.cc1.certificate.domain.Certificate;
import fr.remy.cc1.certificate.domain.CertificateId;
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
        System.out.println(certificate);
        CertificateResponse certificateResponse = new CertificateResponse(certificate.getCertificateId().getValue(), certificate.getName(), certificate.getLink(), certificate.getSkills());
        System.out.println(certificateResponse);
        return ResponseEntity.ok(certificateResponse);
    }
}
