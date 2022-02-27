package fr.remy.cc1.infrastructure.InMemory;

import fr.remy.cc1.domain.UserId;
import fr.remy.cc1.projectTradesmen.domain.scheduler.TradesmanSchedule;

import java.util.Map;

public final class TradesmanSchedulesData {
    private static volatile TradesmanSchedulesData instance;

    public final Map<UserId, TradesmanSchedule> data;

    private TradesmanSchedulesData(Map<UserId, TradesmanSchedule> data) {
        this.data = data;
    }

    public static TradesmanSchedulesData getInstance() {
        synchronized (TradesmanSchedulesData.class) {
            if (instance == null) {
                throw new Error("You need to call TradesmanSchedulesData.setup() first");
            }
        }
        return instance;
    }

    public static void setup(Map<UserId, TradesmanSchedule> data) {
        synchronized (TradesmanSchedulesData.class) {
            instance = new TradesmanSchedulesData(data);
        }
    }
}
