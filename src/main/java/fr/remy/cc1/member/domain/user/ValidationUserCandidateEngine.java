package fr.remy.cc1.member.domain.user;

import fr.remy.cc1.kernel.error.EmailValidatorException;
import fr.remy.cc1.kernel.error.ExceptionsDictionary;
import fr.remy.cc1.kernel.error.PasswordValidatorException;
import fr.remy.cc1.kernel.error.ValidationException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ValidationUserCandidateEngine {

    private final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");

    private final Pattern VALID_PASSWORD_REGEX =
            Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$");

    private static final ValidationUserCandidateEngine INSTANCE = new ValidationUserCandidateEngine();

    private ValidationUserCandidateEngine() { }

    public static ValidationUserCandidateEngine getInstance() {
        return INSTANCE;
    }

    public boolean test(UserCandidate userCandidate) throws ValidationException {
        return this.validateEmail(userCandidate.email) &&
                this.validatePassword(userCandidate.password);
    }

    private boolean validateEmail(Email email) throws EmailValidatorException {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email.email);
        if(matcher.find()) {
            return true;
        }
        throw new EmailValidatorException(ExceptionsDictionary.EMAIL_NOT_VALID.getErrorCode(), ExceptionsDictionary.EMAIL_NOT_VALID.getMessage());
    }

    private boolean validatePassword(Password password) throws PasswordValidatorException {
        Matcher matcher = VALID_PASSWORD_REGEX.matcher(password.password);
        if(matcher.find()) {
            return true;
        }
        throw new PasswordValidatorException(ExceptionsDictionary.PASSWORD_NOT_VALID.getErrorCode(), ExceptionsDictionary.PASSWORD_NOT_VALID.getMessage());
    }
}
