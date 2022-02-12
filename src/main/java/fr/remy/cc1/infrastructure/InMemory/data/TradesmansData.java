package fr.remy.cc1.infrastructure.InMemory.data;

import fr.remy.cc1.member.domain.user.Tradesman.Tradesman;
import fr.remy.cc1.domain.UserId;

import java.util.Map;

public class TradesmansData {

    private static volatile TradesmansData instance;

    public final Map<UserId, Tradesman> data;

    private TradesmansData(Map<UserId, Tradesman> data) {
        this.data = data;
    }

    public static TradesmansData getInstance() {

        synchronized(TradesmansData.class) {
            if (instance == null) {
                throw new Error("you have to use setUp function to use ContractorsData");
            }
            return instance;
        }
    }

    public static void setup(Map<UserId, Tradesman> data) {
        synchronized(TradesmansData.class) {
            instance = new TradesmansData(data);
        }
    }
}
