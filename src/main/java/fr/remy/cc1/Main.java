package fr.remy.cc1;

import fr.remy.cc1.domain.*;
import fr.remy.cc1.domain.event.EventBus;
import fr.remy.cc1.domain.event.Subscriber;
import fr.remy.cc1.domain.mail.UserMailSender;
import fr.remy.cc1.domain.payment.Payment;
import fr.remy.cc1.infrastructure.*;
import fr.remy.cc1.infrastructure.payment.PaymentSuccessfulEvent;
import fr.remy.cc1.infrastructure.payment.PaymentSuccessfulEventSubscription;
import fr.remy.cc1.infrastructure.payment.PaypalPayment;
import fr.remy.cc1.infrastructure.users.InMemoryUsers;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        // à voir comment passer l'objet de l'interface payment pour configurer l'event
        /*Map<Class, List<Subscriber>> subscriptionMap = Map.of(
                RegisterUserEvent.class, Collections.singletonList(new RegisterUserEventSubscription(new UserMailSender())),
                PaySubscriptionEvent.class, Collections.singletonList(new PaySubscriptionEventSubscription(new CreditCardPayment()))
        );*/

        // passer le moyen de paiement autrement que par le paymentEventSubscription
        // un event qui représente le fail du user pour payer
        // un event qui représente le success du user pour payer
        Map<Class, List<Subscriber>> subscriptionMap = Map.of(
                RegisterUserEvent.class, Collections.singletonList(new RegisterUserEventSubscription(new UserMailSender())),
                PaymentSuccessfulEvent.class, Collections.singletonList(new PaymentSuccessfulEventSubscription(new UserMailSender()))
        );


        Payment payment = new PaypalPayment();
        EventBus eventBus = new DefaultEventBus(subscriptionMap);
        Users users = new InMemoryUsers();
        UserService userService = new UserService(users, eventBus);
        final UserId myUserId = users.nextIdentity();
        createUser(userService, myUserId);
        PaymentService paymentService = new PaymentService(payment, eventBus);
        paymentService.verifyPayment(new BigDecimal(12.05));
    }
    private static void createUser(UserService userService, UserId myUserId) {
        User user = User.of(myUserId, "Machavoine", "Rémy", "pomme@pomme.fr", "aZertyu9!");
        userService.create(user);
    }
}
