package fr.remy.cc1;

import fr.remy.cc1.domain.*;
import fr.remy.cc1.domain.event.EventBus;
import fr.remy.cc1.domain.event.Subscriber;
import fr.remy.cc1.domain.mail.UserMailSender;
import fr.remy.cc1.infrastructure.*;
import fr.remy.cc1.infrastructure.payment.PaySubscriptionEvent;
import fr.remy.cc1.infrastructure.payment.PaySubscriptionEventSubscription;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        Map<Class, List<Subscriber>> subscriptionMap = Map.of(
                RegisterUserEvent.class, Collections.singletonList(new RegisterUserEventSubscription(new UserMailSender())),
                PaySubscriptionEvent.class, Collections.singletonList(new PaySubscriptionEventSubscription())
        );

        EventBus eventBus = new DefaultEventBus(subscriptionMap);
        Users users = new InMemoryUsers();
        UserService userService = new UserService(users, eventBus);
        final UserId myUserId = users.nextIdentity();
        createUser(userService, myUserId);
    }

    private static void createUser(UserService userService, UserId myUserId) {
        User user = User.of(myUserId, "Machavoine", "Rémy", "pomme@pomme.fr", "aZertyu9!");
        userService.create(user);
    }
}
