package fr.remy.cc1.projectTradesmen.domain.scheduler;

import fr.remy.cc1.shared.domain.UserId;
import fr.remy.cc1.shared.infrastructure.exceptions.NoSuchEntityException;

import java.util.List;

public interface TradesmanSchedules {
    void save(TradesmanSchedule schedule);

    TradesmanSchedule findByTradesmanId(UserId tradesmanId) throws NoSuchEntityException;

    List<TradesmanSchedule> findAll();
}
