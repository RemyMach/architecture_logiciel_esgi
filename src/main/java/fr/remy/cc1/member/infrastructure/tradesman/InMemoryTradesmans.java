package fr.remy.cc1.member.infrastructure.tradesman;

import fr.remy.cc1.domain.UserId;
import fr.remy.cc1.member.domain.user.Tradesman.Tradesman;
import fr.remy.cc1.member.domain.user.Tradesman.Tradesmans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
