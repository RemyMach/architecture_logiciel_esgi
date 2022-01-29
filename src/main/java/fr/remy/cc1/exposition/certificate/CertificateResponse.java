package fr.remy.cc1.exposition.certificate;

import fr.remy.cc1.domain.certificate.CertificateId;

public class CertificateResponse {

    public final String id;
    public final String name;
    public final String link;

    public CertificateResponse(String id, String name, String link) {
        this.id = id;
        this.name = name;
        this.link = link;
    }

    @Override
    public String toString() {
        return "CertificateResponse{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
