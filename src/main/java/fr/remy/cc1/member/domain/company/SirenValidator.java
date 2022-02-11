package fr.remy.cc1.member.domain.company;

import fr.remy.cc1.kernel.Validator;
import fr.remy.cc1.kernel.error.ValidationException;

public class SirenValidator implements Validator<String> {
    private static final SirenValidator INSTANCE = new SirenValidator();

    private SirenValidator() { }

    public static SirenValidator getInstance() {
        return INSTANCE;
    }
    @Override
    public boolean test(String aspirant) throws ValidationException {
        return aspirant.replace(" ", "").length() == 9;
    }
}
