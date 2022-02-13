package fr.remy.cc1.certificate.exposition;

import fr.remy.cc1.domain.skill.Skill;

import java.util.List;

public class CertificateResponse {

    public final String id;
    public final String name;
    public final String link;
    public final List<Skill> skills;

    public CertificateResponse(String id, String name, String link, List<Skill> skills) {
        this.id = id;
        this.name = name;
        this.link = link;
        this.skills = skills;
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
