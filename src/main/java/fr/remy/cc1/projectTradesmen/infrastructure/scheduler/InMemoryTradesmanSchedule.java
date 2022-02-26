package fr.remy.cc1.projectTradesmen.infrastructure.scheduler;

import fr.remy.cc1.domain.UserId;
import fr.remy.cc1.infrastructure.exceptions.InfrastructureExceptionsDictionary;
import fr.remy.cc1.infrastructure.exceptions.NoSuchEntityException;
import fr.remy.cc1.projectTradesmen.domain.scheduler.TradesmanSchedule;
import fr.remy.cc1.projectTradesmen.domain.scheduler.TradesmanSchedules;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class InMemoryTradesmanSchedule implements TradesmanSchedules {
    private final Map<UserId, TradesmanSchedule> tradesmanSchedulesData;

    public InMemoryTradesmanSchedule(Map<UserId, TradesmanSchedule> tradesmanSchedulesData) {
        this.tradesmanSchedulesData = tradesmanSchedulesData;
    }

    @Override
    public void save(TradesmanSchedule schedule) {
        tradesmanSchedulesData.put(schedule.getTradesmanId(), schedule);
    }

    @Override
    public TradesmanSchedule findByTradesmanId(UserId tradesmanId) throws NoSuchEntityException {
        final TradesmanSchedule tradesmanSchedule = tradesmanSchedulesData.get(tradesmanId);
        if (tradesmanSchedule == null) {
            throw new NoSuchEntityException(InfrastructureExceptionsDictionary.TRADESMAN_SCHEDULE_NOT_FOUND.getErrorCode(), InfrastructureExceptionsDictionary.TRADESMAN_SCHEDULE_NOT_FOUND.getMessage());
        }
        return tradesmanSchedule;
    }

    @Override
    public List<TradesmanSchedule> findAll() {
        return new ArrayList<>(tradesmanSchedulesData.values());
    }
}
