package fr.remy.cc1;

import fr.remy.cc1.domain.*;
import fr.remy.cc1.domain.event.EventBus;
import fr.remy.cc1.domain.mail.UserMailSender;
import fr.remy.cc1.domain.payment.ApproveTradesman;
import fr.remy.cc1.domain.payment.Contractor;
import fr.remy.cc1.domain.payment.PaymentProcess;
import fr.remy.cc1.infrastructure.DefaultEventBus;
import fr.remy.cc1.infrastructure.InMemoryUsers;
import fr.remy.cc1.infrastructure.RegisterUserEvent;
import fr.remy.cc1.infrastructure.RegisterUserEventSubscription;

import java.util.Collections;

public class Main {

    public static void main(String[] args) {

        var subscriptionMap =
                Collections.singletonMap(RegisterUserEvent.class,
                        Collections.singletonList(new RegisterUserEventSubscription(new UserMailSender())));
        EventBus eventBus = new DefaultEventBus(subscriptionMap);
        Users users = new InMemoryUsers();
        Handler approveTradesman = new ApproveTradesman();
        Handler contractor = new Contractor();
        Handler paymentProcess = new PaymentProcess();
        paymentProcess.setNext(approveTradesman);
        approveTradesman.setNext(contractor);
        UserService userService = new UserService(users, eventBus, paymentProcess);
        final UserId myUserId = users.nextIdentity();
        createUser(userService, myUserId);
    }

    private static void createUser(UserService userService, UserId myUserId) {
        User user = User.of(myUserId, "Machavoine", "Rémy", "pomme@pomme.fr", "aZertyu9!");
        userService.create(user);
    }
}
