package fr.remy.cc1.infrastructure.creditcards;
import fr.remy.cc1.domain.CreditCard;
import fr.remy.cc1.domain.CreditCards;
import fr.remy.cc1.domain.payment.CreditCardId;
import fr.remy.cc1.domain.user.User;
import fr.remy.cc1.domain.user.UserId;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryCreditCards implements CreditCards {
    private final AtomicInteger counter = new AtomicInteger(0);
    private final Map<CreditCardId, CreditCard> data = new ConcurrentHashMap<>();

    @Override
    public void save(CreditCard creditCard, User user) {
        System.out.println("on save une credit card");
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
}
