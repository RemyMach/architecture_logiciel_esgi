package fr.remy.cc1.member.infrastructure.tradesman;

import fr.remy.cc1.member.domain.user.Tradesman.Tradesman;
import fr.remy.cc1.member.domain.user.Tradesman.Tradesmans;
import fr.remy.cc1.member.domain.user.UserId;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryTradesmans implements Tradesmans {
    private final Map<UserId, Tradesman> tradesmansData;

    public InMemoryTradesmans(Map<UserId, Tradesman> tradesmansData) {
        this.tradesmansData = tradesmansData;
    }

    @Override
    public void save(Tradesman tradesman) {
        tradesmansData.put(tradesman.getUserId(), tradesman);
    }

    @Override
    public List<Tradesman> findAll() {
        return new ArrayList<>(tradesmansData.values());
    }
}
