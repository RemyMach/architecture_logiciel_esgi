package fr.remy.cc1.domain.user;

import fr.remy.cc1.kernel.error.BasicException;
import fr.remy.cc1.kernel.error.ValidationException;

public final class User {

    private final UserId userId;
    private final String lastname;
    private final String firstname;
    private final Email email;
    private final Password password;
    private final UserCategory userCategory;

    private User(UserId userId, String lastname, String firstname, Email email, Password password, UserCategory userCategory) {
        this.userId = userId;
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.password = password;
        this.userCategory = userCategory;
    }

    public static User of(UserId userId, String lastname, String firstname, Email email, Password password, UserCategory userCategory) throws ValidationException {
        User user = new User(userId, lastname, firstname, email,  password, userCategory);
        if(ValidationUserEngine.getInstance().test(user)) {
            return user;
        }
        return null;
    }

    public UserId getUserId() {
        return userId;
    }

    public Email getEmail() { return email; }

    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public Password getPassword() { return password; }
}
