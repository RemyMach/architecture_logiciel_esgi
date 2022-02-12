package fr.remy.cc1.certificate.infrastructure;

import fr.remy.cc1.certificate.domain.Certificate;
import fr.remy.cc1.certificate.domain.CertificateId;
import fr.remy.cc1.certificate.domain.Certificates;
import fr.remy.cc1.member.domain.user.UserId;
import fr.remy.cc1.infrastructure.exceptions.InfrastructureExceptionsDictionary;
import fr.remy.cc1.infrastructure.exceptions.NoSuchEntityException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class InMemoryCertificates implements Certificates {

    private final AtomicInteger counter = new AtomicInteger(0);
    private final Map<UserId, List<Certificate>> usersCertificate = new ConcurrentHashMap<>();

    @Override
    public void save(UserId userId, Certificate certificate) {
        List<Certificate> certificates = new ArrayList<Certificate>();
        try {
            certificates = this.byUserId(userId);
        } catch (NoSuchEntityException noSuchEntityException) {}

        certificates.add(certificate);
        usersCertificate.put(userId, certificates);
    }

    @Override
    public List<Certificate> byUserId(UserId userId) throws NoSuchEntityException {
        final List<Certificate> certificates = usersCertificate.get(userId);
        if (certificates == null) {
            throw new NoSuchEntityException(InfrastructureExceptionsDictionary.CERTIFICATE_NOT_FOUND.getErrorCode(), InfrastructureExceptionsDictionary.CERTIFICATE_NOT_FOUND.getMessage());
        }
        return certificates;
    }

    @Override
    public Certificate byId(CertificateId certificateId) throws NoSuchEntityException {
        return this.findAll().stream()
                .filter(certificate -> certificate.getCertificateId().equals(certificateId))
                .findFirst()
                .orElseThrow(
                        () ->  new NoSuchEntityException(InfrastructureExceptionsDictionary.CERTIFICATE_NOT_FOUND.getErrorCode(), InfrastructureExceptionsDictionary.CERTIFICATE_NOT_FOUND.getMessage())
                );
    }

    @Override
    public CertificateId nextIdentity() {
        int counter1 = counter.incrementAndGet();
        System.out.println(counter1);
        return CertificateId.of(counter1);
    }

    @Override
    public List<Certificate> findAll() {
        return usersCertificate.values()
                .stream()
                .flatMap(s -> s.stream())
                .collect(Collectors.toList());
    }
}
