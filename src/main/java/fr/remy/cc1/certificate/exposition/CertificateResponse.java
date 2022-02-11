package fr.remy.cc1.certificate.exposition;

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
