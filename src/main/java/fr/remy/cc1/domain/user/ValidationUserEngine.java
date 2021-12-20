package fr.remy.cc1.domain.user;

import fr.remy.cc1.kernel.error.BasicException;
import fr.remy.cc1.kernel.error.EmailValidatorException;
import fr.remy.cc1.kernel.error.ExceptionsDictionary;
import fr.remy.cc1.kernel.error.PasswordValidatorException;

import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ValidationUserEngine {

    private final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");

    private final Pattern VALID_PASSWORD_REGEX =
            Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$");

    private static final ValidationUserEngine INSTANCE = new ValidationUserEngine();

    private ValidationUserEngine() { }

    public static ValidationUserEngine getInstance() {
        return INSTANCE;
    }

    public boolean test(User user) throws BasicException {
        return this.validateEmail(user.getEmail()) &&
                this.validatePassword(user.getPassword());
    }

    private boolean validateEmail(String email) throws EmailValidatorException {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        if(matcher.find()) {
            return true;
        }
        throw new EmailValidatorException(ExceptionsDictionary.EMAIL_NOT_VALID.getErrorCode(), ExceptionsDictionary.EMAIL_NOT_VALID.getMessage());
    }

    private boolean validatePassword(String password) throws PasswordValidatorException {
        Matcher matcher = VALID_PASSWORD_REGEX.matcher(password);
        if(matcher.find()) {
            return true;
        }
        throw new PasswordValidatorException(ExceptionsDictionary.PASSWORD_NOT_VALID.getErrorCode(), ExceptionsDictionary.PASSWORD_NOT_VALID.getMessage());
    }
}
