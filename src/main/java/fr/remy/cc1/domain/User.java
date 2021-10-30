package fr.remy.cc1.domain;

import java.util.Objects;

public final class User {

    private final UserId userId;
    private final String lastname;
    private final String firstname;
    private String password;

    private User(UserId userId, String lastname, String firstname, String password) {
        this.userId = userId;
        this.lastname = lastname;
        this.firstname = firstname;
        this.password = password;
    }

    public static User of(UserId userId, String lastname, String firstname, String password) {
        return new User(userId, lastname, firstname, password);
    }

    public UserId getUserId() {
        return userId;
    }

    public void changePassword(String newPassword) {
        this.password = Objects.requireNonNull(newPassword);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
