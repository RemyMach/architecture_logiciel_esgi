package fr.remy.cc1.member.domain.user;

public interface IUser {

    UserId getUserId();

    Email getEmail();

    String getLastname();

    String getFirstname();

    Password getPassword();
}
