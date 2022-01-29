package fr.remy.cc1.infrastructure.certificates;

import fr.remy.cc1.domain.certificate.Certificate;
import fr.remy.cc1.domain.certificate.CertificateId;
import fr.remy.cc1.domain.certificate.Certificates;
import fr.remy.cc1.domain.user.UserId;
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

        usersCertificate.put(userId, certificates);
    }

    @Override
    public List<Certificate> byUserId(UserId userId) throws NoSuchEntityException {
        final List<Certificate> certificates = usersCertificate.get(userId);
        if (certificates == null) {
            throw new NoSuchEntityException(InfrastructureExceptionsDictionary.USER_NOT_FOUND.getErrorCode(), InfrastructureExceptionsDictionary.USER_NOT_FOUND.getMessage());
        }
        return certificates;
    }

    @Override
    public CertificateId nextIdentity() {
        return CertificateId.of(counter.incrementAndGet());
    }

    @Override
    public List<Certificate> findAll() {
        return usersCertificate.values()
                .stream()
                .flatMap(s -> s.stream())
                .collect(Collectors.toList());
    }
}
