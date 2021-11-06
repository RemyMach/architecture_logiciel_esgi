package fr.remy.cc1.domain;

import fr.remy.cc1.domain.user.User;

import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUserEngine implements Predicate<User> {

    private final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");

    private final Pattern VALID_PASSWORD_REGEX =
            Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$");

    private static final ValidationUserEngine INSTANCE = new ValidationUserEngine();

    private ValidationUserEngine() { }

    public static ValidationUserEngine getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean test(User user) {
        return this.validateEmail(user.getEmail()) &&
                this.validatePassword(user.getPassword());
    }

    private boolean validateEmail(String email) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();
    }

    private boolean validatePassword(String password) {
        Matcher matcher = VALID_PASSWORD_REGEX.matcher(password);
        return matcher.find();
    }
}
