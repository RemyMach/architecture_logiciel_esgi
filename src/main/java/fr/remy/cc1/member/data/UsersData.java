package fr.remy.cc1.member.data;

import fr.remy.cc1.member.domain.user.User;
import fr.remy.cc1.member.domain.user.UserId;
import fr.remy.cc1.member.domain.user.contractor.Contractor;

import java.util.Map;

public class UsersData {

    private static volatile UsersData instance;

    public final Map<UserId, User> data;

    private UsersData(Map<UserId, User> data) {
        this.data = data;
    }

    public static UsersData getInstance() {

        synchronized(UsersData.class) {
            if (instance == null) {
                throw new Error("you have to use setUp function to use ContractorsData");
            }
            return instance;
        }
    }

    public static void setup(Map<UserId, User> data) {
        synchronized(UsersData.class) {
            instance = new UsersData(data);
        }
    }
}
