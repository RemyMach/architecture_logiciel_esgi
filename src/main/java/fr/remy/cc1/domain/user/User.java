package fr.remy.cc1.domain.user;

import fr.remy.cc1.domain.ValidationUserEngine;

public final class User {

    private final UserId userId;
    private final String lastname;
    private final String firstname;
    private final String email;
    private final String password;

    private User(UserId userId, String lastname, String firstname, String email, String password) {
        this.userId = userId;
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.password = password;
    }

    public static User of(UserId userId, String lastname, String firstname, String email, String password) {
        User user = new User(userId, lastname, firstname, email,  password);
        if(ValidationUserEngine.getInstance().test(user)) {
            return user;
        }
        throw new IllegalArgumentException("Illegal arguments");
    }

    public UserId getUserId() {
        return userId;
    }

    public String getEmail() { return email; }

    public String getPassword() { return password; }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
