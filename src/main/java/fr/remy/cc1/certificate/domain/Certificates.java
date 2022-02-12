package fr.remy.cc1.certificate.domain;

import fr.remy.cc1.domain.UserId;
import fr.remy.cc1.infrastructure.exceptions.NoSuchEntityException;

import java.util.List;

public interface Certificates {
    void save(UserId userId, Certificate certificate);

    List<Certificate> byUserId(UserId userId) throws NoSuchEntityException;

    Certificate byId(CertificateId certificateId) throws NoSuchEntityException;

    CertificateId nextIdentity();

    List<Certificate> findAll();
}
