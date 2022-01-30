package fr.remy.cc1.domain.certificate;

import fr.remy.cc1.domain.skill.Skill;

import java.util.List;

public class Certificate {

    private final CertificateId certificateId;
    private final String name;
    private final String link;
    private final List<Skill> skills;

    private Certificate(CertificateId certificateId, String name, String link, List<Skill> skills) {
        this.certificateId = certificateId;
        this.name = name;
        this.link = link;
        this.skills = skills;
    }

    public static Certificate of(CertificateId certificateId, String name, String link, List<Skill> skills) {
        return new Certificate(certificateId, name, link, skills);
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
