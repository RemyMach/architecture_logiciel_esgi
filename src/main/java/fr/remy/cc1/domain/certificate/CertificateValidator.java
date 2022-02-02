package fr.remy.cc1.domain.certificate;

import fr.remy.cc1.domain.skill.Skill;
import fr.remy.cc1.kernel.Validator;
import fr.remy.cc1.kernel.error.ValidationException;

import java.util.List;

public class CertificateValidator implements Validator<List<Skill>> {
    private static final CertificateValidator INSTANCE = new CertificateValidator();

    private CertificateValidator() { }

    public static CertificateValidator getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean test(List<Skill> aspirant) throws ValidationException {
        return aspirant != null && aspirant.size() > 0;
    }
}