package fr.remy.cc1.certificate;

import fr.remy.cc1.certificate.domain.Certificate;
import fr.remy.cc1.certificate.domain.Certificates;
import fr.remy.cc1.domain.UserId;
import fr.remy.cc1.member.domain.user.Users;
import fr.remy.cc1.certificate.infrastructure.InMemoryCertificates;
import fr.remy.cc1.infrastructure.exceptions.NoSuchEntityException;
import fr.remy.cc1.member.infrastructure.user.InMemory.InMemoryUsers;
import fr.remy.cc1.kernel.error.ExceptionsDictionary;
import fr.remy.cc1.kernel.error.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class CertificateCreationValidationTest {

    Certificate certificate;
    Certificates certificates;
    Users users;
    UserId userId;

    @BeforeEach
    void setup(){
        users = new InMemoryUsers(new ConcurrentHashMap<>());
        certificates = new InMemoryCertificates();
    }

    @Test
    @DisplayName("when the certificate has no skills")
    void certificateHasNoSkills() throws NoSuchEntityException {
        try {
            certificate = Certificate.of(certificates.nextIdentity(), "charlo", "http://linkverslecertificat", List.of());
            userId = users.nextIdentity();
            fail("should Fail");
        }catch(ValidationException validationException) {
            assertEquals(validationException.getErrorCode(), ExceptionsDictionary.CERTIFICATE_SKILLS_VALIDATION_ERROR.getErrorCode());
        }
    }

    @Test
    @DisplayName("when the certificate has skills to null")
    void certificateHasSkillsToNull() throws NoSuchEntityException {
        try {
            certificate = Certificate.of(certificates.nextIdentity(), "charlo", "http://linkverslecertificat", null);
            userId = users.nextIdentity();
            fail("should Fail");
        }catch(ValidationException validationException) {
            assertEquals(validationException.getErrorCode(), ExceptionsDictionary.CERTIFICATE_SKILLS_VALIDATION_ERROR.getErrorCode());
        }
    }

}
