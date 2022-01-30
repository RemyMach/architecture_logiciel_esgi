package fr.remy.cc1.infrastructure.tradesman;

import fr.remy.cc1.domain.user.Tradesman.Tradesman;
import fr.remy.cc1.domain.user.Tradesman.Tradesmans;
import fr.remy.cc1.domain.user.UserId;
import fr.remy.cc1.domain.user.contractor.Contractor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryTradesmans implements Tradesmans {
    private final Map<UserId, Tradesman> tradesmansData = new ConcurrentHashMap<>();

    @Override
    public void save(Tradesman tradesman) {
        tradesmansData.put(tradesman.getUserId(), tradesman);
    }

    @Override
    public List<Tradesman> findAll() {
        return new ArrayList<>(tradesmansData.values());
    }
}
