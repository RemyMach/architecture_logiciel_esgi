package fr.remy.cc1.infrastructure;

import fr.remy.cc1.certificate.domain.Certificate;
import fr.remy.cc1.certificate.domain.CertificateId;
import fr.remy.cc1.certificate.domain.Certificates;
import fr.remy.cc1.infrastructure.InMemory.UserSubscriptionsData;
import fr.remy.cc1.infrastructure.InMemory.UsersData;
import fr.remy.cc1.domain.skill.Skill;
import fr.remy.cc1.domain.UserId;
import fr.remy.cc1.member.domain.user.Users;
import fr.remy.cc1.certificate.infrastructure.InMemoryCertificates;
import fr.remy.cc1.infrastructure.exceptions.NoSuchEntityException;
import fr.remy.cc1.member.infrastructure.user.InMemoryUsers;
import fr.remy.cc1.kernel.error.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class InMemoryCertificateTest {

    Certificate certificate;
    UserId userId;
    Certificates certificates;
    Users users;

    @BeforeEach
    void setup() {
        UsersData.setup(new ConcurrentHashMap<>());
        UserSubscriptionsData.setup(new ConcurrentHashMap<>());
        this.users = new InMemoryUsers(UsersData.getInstance().data, UserSubscriptionsData.getInstance().data);
        certificates = new InMemoryCertificates();
    }

    @Test
    @DisplayName("the certificate is create")
    void certificateIsCreate() throws NoSuchEntityException, ValidationException {
        certificate = Certificate.of(certificates.nextIdentity(), "charlo", "http://linkverslecertificat", List.of(Skill.of("plaquo")));
        userId = users.nextIdentity();
        try {
            assertEquals(certificates.byUserId(userId).size(), 0);
            fail("should Fail");
        }catch(NoSuchEntityException noSuchEntityException) {}

        certificates.save(userId, certificate);

        assertEquals(certificates.byUserId(userId).size(), 1);
        assertEquals(certificates.byUserId(userId), List.of(certificate));
    }

    @Test
    @DisplayName("2 certificate are create for the same user")
    void TwoCertificateForTheSameUser() throws NoSuchEntityException, ValidationException {
        certificate = Certificate.of(certificates.nextIdentity(), "charlo", "http://linkverslecertificat", List.of(Skill.of("plaquo")));
        Certificate certificate2 = Certificate.of(certificates.nextIdentity(), "charlo2", "http://linkverslecertificat2", List.of(Skill.of("plaquo")));
        userId = users.nextIdentity();
        try {
            assertEquals(certificates.byUserId(userId).size(), 0);
            fail("should Fail");
        }catch(NoSuchEntityException noSuchEntityException) {}

        certificates.save(userId, certificate);
        certificates.save(userId, certificate2);

        assertEquals(certificates.byUserId(userId).size(), 2);
        assertEquals(certificates.byUserId(userId), List.of(certificate, certificate2));
    }

    @Test
    @DisplayName("2 certificate are create for the same user and one for another")
    void TwoCertificateForTheSameUserAndOneForAnOther() throws NoSuchEntityException, ValidationException {
        certificate = Certificate.of(certificates.nextIdentity(), "charlo", "http://linkverslecertificat", List.of(Skill.of("plaquo")));
        Certificate certificate2 = Certificate.of(certificates.nextIdentity(), "charlo2", "http://linkverslecertificat2",List.of(Skill.of("plaquo")));
        Certificate certificate3 = Certificate.of(certificates.nextIdentity(), "pomme", "http://lapomme", List.of(Skill.of("plaquo")));
        userId = users.nextIdentity();
        UserId userId2 = users.nextIdentity();
        try {
            assertEquals(certificates.byUserId(userId).size(), 0);
            assertEquals(certificates.byUserId(userId2).size(), 0);
            fail("should Fail");
        }catch(NoSuchEntityException noSuchEntityException) {}

        certificates.save(userId, certificate);
        certificates.save(userId, certificate2);
        certificates.save(userId2, certificate3);

        assertEquals(certificates.byUserId(userId).size(), 2);
        assertEquals(certificates.byUserId(userId2).size(), 1);
        assertEquals(certificates.findAll().size(), 3);
        assertEquals(certificates.byUserId(userId), List.of(certificate, certificate2));
        assertEquals(certificates.byUserId(userId2), List.of(certificate3));
        assertEquals(certificates.findAll(), List.of(certificate, certificate2, certificate3));
    }

    @Test
    @DisplayName("get a certificate by it's creation certificateId")
    void getCertificateByCertificateId() throws NoSuchEntityException, ValidationException {
        certificate = Certificate.of(CertificateId.of(1), "charlo", "http://linkverslecertificat", List.of(Skill.of("plaquo")));
        userId = users.nextIdentity();
        try {
            assertEquals(certificates.byId(CertificateId.of(1)).getCertificateId(), CertificateId.of(1));
            fail("should Fail");
        }catch(NoSuchEntityException noSuchEntityException) {}

        certificates.save(userId, certificate);

        assertEquals(certificates.byId(CertificateId.of(1)).getCertificateId(), CertificateId.of(1));
    }

}
