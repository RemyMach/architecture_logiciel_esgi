package fr.remy.cc1;

import fr.remy.cc1.domain.*;
import fr.remy.cc1.domain.event.EventBus;
import fr.remy.cc1.domain.event.Subscriber;
import fr.remy.cc1.domain.mail.Mail;
import fr.remy.cc1.domain.payment.Invoices;
import fr.remy.cc1.domain.payment.Payment;
import fr.remy.cc1.infrastructure.*;
import fr.remy.cc1.infrastructure.mail.SandboxMail;
import fr.remy.cc1.infrastructure.payment.*;
import fr.remy.cc1.infrastructure.users.InMemoryUsers;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        Mail sandboxMail = new SandboxMail();
        Invoices invoices = new InMemoryInvoices();
        Users users = new InMemoryUsers();
        List<Subscriber> subscriptionSuccessfulEventSubscriptions = Arrays.asList(
                new SubscriptionSuccessfulEventMessengerSubscription(sandboxMail),
                new SubscriptionSuccessfulEventInvoiceSubscription(invoices),
                new SubscriptionSuccessfulEventUserSubscription(users)
        );
        Map<Class, List<Subscriber>> subscriptionMap = Map.of(
                RegisterUserEvent.class, Collections.singletonList(new RegisterUserEventMessengerSubscription(sandboxMail)),
                SubscriptionSuccessfulEvent.class, Collections.unmodifiableList(subscriptionSuccessfulEventSubscriptions)
        );

        CreditCard creditCard = CreditCard.of(1234567262, 1203, 321, "POMME");
        Payment payment = new CreditCardPayment(creditCard);
        EventBus eventBus = new DefaultEventBus(subscriptionMap);
        UserService userService = new UserService(users, eventBus);
        PaymentService paymentService = new PaymentService(payment, eventBus);
        final UserId myUserId = users.nextIdentity();
        createUser(userService, myUserId, paymentService);
    }

    private static void createUser(UserService userService, UserId myUserId, PaymentService paymentService) {
        User user = User.of(myUserId, "Machavoine", "Rémy", "pomme@pomme.fr", "aZertyu9!");
        userService.create(user);
        paySubscriptionOffer(user, paymentService);
    }

    private static void paySubscriptionOffer(User user, PaymentService paymentService) {
        SubscriptionOffer subscriptionOffer = SubscriptionOffer.of(new BigDecimal(12.05),10);
        paymentService.paySubscription(subscriptionOffer,  Currency.EUR, user);
    }
}
