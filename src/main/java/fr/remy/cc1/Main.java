package fr.remy.cc1;

import fr.remy.cc1.domain.*;
import fr.remy.cc1.domain.event.Event;
import fr.remy.cc1.domain.event.EventBus;
import fr.remy.cc1.domain.event.Subscriber;
import fr.remy.cc1.domain.mail.UserMailSender;
import fr.remy.cc1.domain.payment.ApproveTradesman;
import fr.remy.cc1.domain.payment.Contractor;
import fr.remy.cc1.domain.payment.PaymentProcess;
import fr.remy.cc1.infrastructure.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        Map<Class, List<Subscriber>> subscriptionMap = new HashMap<Class, List<Subscriber>>();
        subscriptionMap.put(
                RegisterUserEvent.class,
                Collections.singletonList(new RegisterUserEventSubscription(new UserMailSender()))
        );
        subscriptionMap.put(
                PaySubscriptionEvent.class,
                Collections.singletonList(new PaySubscriptionEventSubscription())
        );
        /*var subscriptionMap =
                Collections.singletonMap(RegisterUserEvent.class,
                        Collections.singletonList(
                                new RegisterUserEventSubscription(new UserMailSender())
                                ));*/

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
