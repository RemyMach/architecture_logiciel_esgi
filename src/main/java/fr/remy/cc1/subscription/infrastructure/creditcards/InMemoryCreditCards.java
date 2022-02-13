package fr.remy.cc1.subscription.infrastructure.creditcards;

import fr.remy.cc1.domain.UserId;
import fr.remy.cc1.subscription.domain.creditcard.CreditCard;
import fr.remy.cc1.subscription.domain.creditcard.CreditCardId;
import fr.remy.cc1.subscription.domain.creditcard.CreditCards;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryCreditCards implements CreditCards {
    private final AtomicInteger counter = new AtomicInteger(0);
    private final Map<CreditCardId, CreditCard> data = new ConcurrentHashMap<>();

    @Override
    public void save(CreditCard creditCard, UserId userId) {
        data.put(creditCard.getCreditCardId(), creditCard);
    }

    @Override
    public CreditCardId nextIdentity() {
        return CreditCardId.of(counter.incrementAndGet());
    }

    @Override
    public CreditCard byId(CreditCardId creditCardId) {
        return data.get(creditCardId);
    }

    @Override
    public CreditCard findByUserId(UserId userId) {
        return data.values().stream().filter(entry -> entry.getUserId().equals(userId)).findFirst().orElseThrow();
    }

    @Override
    public void delete(CreditCardId creditCardId) {
        data.remove(creditCardId);
    }
}
