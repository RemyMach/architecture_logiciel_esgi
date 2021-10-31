package fr.remy.cc1;

import fr.remy.cc1.domain.User;
import fr.remy.cc1.domain.UserId;
import fr.remy.cc1.domain.UserService;
import fr.remy.cc1.domain.Users;
import fr.remy.cc1.infrastructure.InMemoryUsers;

public class Main {

    public static void main(String[] args) {
        Users users = new InMemoryUsers();
        /*UserService userService = new UserService(users);

        final UserId myUserId = users.nextIdentity();

        createUser(userService, myUserId);*/
    }

    private static void createUser(UserService userService, UserId myUserId) {
        User user = User.of(myUserId, "Machavoine", "Rémy", "pomme@pomme.fr", "azertyu9!");
        userService.create(user);
    }
}
