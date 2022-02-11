package fr.remy.cc1.infrastructure.user;

import fr.remy.cc1.member.domain.customer.SubscriptionOffer;
import fr.remy.cc1.member.domain.user.*;
import fr.remy.cc1.infrastructure.exceptions.NoSuchEntityException;

import java.util.List;

public class MysqlUsers implements Users {
    @Override
    public void save(User user) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    @Override
    public void saveSubscriptionOffer(UserId userId, SubscriptionOffer subscriptionOffer) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    @Override
    public User byId(UserId userId) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    @Override
    public User byEmailAndPassword(Email email, Password password) throws NoSuchEntityException {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    @Override
    public UserId nextIdentity() {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    @Override
    public List<User> findAll() {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    @Override
    public List<User> findAllByPaidSinceMoreThanCertainMonthAgo(int months) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    @Override
    public SubscriptionOffer getSubscriptionOffer(UserId userId) { throw new UnsupportedOperationException("Not yet implemented."); }
}
