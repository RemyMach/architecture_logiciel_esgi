package fr.remy.cc1.member.domain.user;

import fr.remy.cc1.domain.UserId;

public interface IUser {

    UserId getUserId();

    Email getEmail();

    String getLastname();

    String getFirstname();

    Password getPassword();
}
