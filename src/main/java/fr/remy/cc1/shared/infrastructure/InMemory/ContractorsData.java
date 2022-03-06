package fr.remy.cc1.shared.infrastructure.InMemory;

import fr.remy.cc1.member.domain.user.contractor.Contractor;
import fr.remy.cc1.shared.domain.UserId;

import java.util.Map;

public class ContractorsData {

    private static volatile ContractorsData instance;

    public final Map<UserId, Contractor> data;

    private ContractorsData(Map<UserId, Contractor> data) {
        this.data = data;
    }

    public static ContractorsData getInstance() {

        synchronized(ContractorsData.class) {
            if (instance == null) {
                throw new Error("you have to use setUp function to use ContractorsData");
            }
            return instance;
        }
    }

    public static void setup(Map<UserId, Contractor> data) {
        synchronized(ContractorsData.class) {
            instance = new ContractorsData(data);
        }
    }
}
