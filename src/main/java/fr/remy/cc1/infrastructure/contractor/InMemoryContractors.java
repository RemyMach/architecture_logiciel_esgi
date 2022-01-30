package fr.remy.cc1.infrastructure.contractor;

import fr.remy.cc1.domain.user.UserId;
import fr.remy.cc1.domain.user.contractor.Contractor;
import fr.remy.cc1.domain.user.contractor.Contractors;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryContractors implements Contractors {
    private final Map<UserId, Contractor> contractorsData = new ConcurrentHashMap<>();

    @Override
    public void save(Contractor contractor) {
        contractorsData.put(contractor.getUserId(), contractor);
    }

    @Override
    public List<Contractor> findAll() {
        return new ArrayList<>(contractorsData.values());
    }
}
