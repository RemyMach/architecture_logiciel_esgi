package fr.remy.cc1.infrastructure;

import fr.remy.cc1.domain.certificate.Certificate;
import fr.remy.cc1.domain.certificate.Certificates;
import fr.remy.cc1.domain.user.UserId;
import fr.remy.cc1.domain.user.Users;
import fr.remy.cc1.infrastructure.certificates.InMemoryCertificates;
import fr.remy.cc1.infrastructure.exceptions.NoSuchEntityException;
import fr.remy.cc1.infrastructure.user.InMemoryUsers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class InMemoryCertificateTest {

    Certificate certificate;
    UserId userId;
    Certificates certificates;
    Users users = new InMemoryUsers();

    @BeforeEach
    void setup(){
         certificates = new InMemoryCertificates();
    }

    @Test
    @DisplayName("the certificate is create")
    void certificateIsCreate() throws NoSuchEntityException {
        certificate = Certificate.of(certificates.nextIdentity(), "charlo", "http://linkverslecertificat");
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
    void TwoCertificateForTheSameUser() throws NoSuchEntityException {
        certificate = Certificate.of(certificates.nextIdentity(), "charlo", "http://linkverslecertificat");
        Certificate certificate2 = Certificate.of(certificates.nextIdentity(), "charlo2", "http://linkverslecertificat2");
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
    void TwoCertificateForTheSameUserAndOneForAnOther() throws NoSuchEntityException {
        certificate = Certificate.of(certificates.nextIdentity(), "charlo", "http://linkverslecertificat");
        Certificate certificate2 = Certificate.of(certificates.nextIdentity(), "charlo2", "http://linkverslecertificat2");
        Certificate certificate3 = Certificate.of(certificates.nextIdentity(), "pomme", "http://lapomme");
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

}
