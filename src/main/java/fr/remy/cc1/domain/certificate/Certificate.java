package fr.remy.cc1.domain.certificate;

public class Certificate {

    private final CertificateId certificateId;
    private final String name;
    private final String link;

    private Certificate(CertificateId certificateId, String name, String link) {
        this.certificateId = certificateId;
        this.name = name;
        this.link = link;
    }

    public static Certificate of(CertificateId certificateId, String name, String link) {
        return new Certificate(certificateId, name, link);
    }

    public String getName() {
        return name;
    }

    public String getLink() {
        return link;
    }

    public CertificateId getCertificateId() {
        return certificateId;
    }
}
