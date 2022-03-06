package fr.remy.cc1.shared.domain;

import fr.remy.cc1.kernel.error.ValidationException;
import fr.remy.cc1.member.domain.user.Email;
import fr.remy.cc1.member.domain.user.IUser;
import fr.remy.cc1.member.domain.user.Password;
import fr.remy.cc1.member.domain.user.UserCategory;

public final class User implements IUser {

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
        return new User(userId, lastname, firstname, email,  password, userCategory);
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

    public UserCategory getUserCategory() {
        return userCategory;
    }
}
