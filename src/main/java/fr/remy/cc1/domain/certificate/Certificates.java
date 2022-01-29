package fr.remy.cc1.domain.certificate;

import fr.remy.cc1.domain.user.UserId;
import fr.remy.cc1.infrastructure.exceptions.NoSuchEntityException;

import java.util.List;

public interface Certificates {
    void save(UserId userId, Certificate certificate);

    List<Certificate> byUserId(UserId userId) throws NoSuchEntityException;

    CertificateId nextIdentity();

    List<Certificate> findAll();
}
