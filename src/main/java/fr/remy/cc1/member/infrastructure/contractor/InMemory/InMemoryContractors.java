package fr.remy.cc1.member.infrastructure.contractor.InMemory;

import fr.remy.cc1.domain.UserId;
import fr.remy.cc1.member.domain.user.contractor.Contractor;
import fr.remy.cc1.member.domain.user.contractor.Contractors;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InMemoryContractors implements Contractors {

    private final Map<UserId, Contractor> contractorsData;

    public InMemoryContractors(Map<UserId, Contractor> contractorsData) {
        this.contractorsData = contractorsData;
    }

    @Override
    public void save(Contractor contractor) {
        contractorsData.put(contractor.getUserId(), contractor);
    }

    @Override
    public List<Contractor> findAll() {
        return new ArrayList<>(contractorsData.values());
    }
}
