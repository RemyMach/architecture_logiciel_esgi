package fr.remy.cc1.domain.user;

public interface IUser {

    UserId getUserId();

    Email getEmail();

    String getLastname();

    String getFirstname();

    Password getPassword();
}
