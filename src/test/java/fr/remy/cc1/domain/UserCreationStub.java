package fr.remy.cc1.domain;

import fr.remy.cc1.domain.event.Subscriber;
import fr.remy.cc1.domain.mail.MockEmailSender;
import fr.remy.cc1.domain.invoice.Invoices;
import fr.remy.cc1.domain.payment.creditcard.CreditCards;
import fr.remy.cc1.domain.user.Users;
import fr.remy.cc1.domain.user.RegisterUserEvent;
import fr.remy.cc1.domain.customer.SubscriptionSuccessfulEvent;
import fr.remy.cc1.infrastructure.UserCreationEventBus;
import fr.remy.cc1.domain.payment.creditcard.SaveCreditCardEvent;
import fr.remy.cc1.domain.payment.creditcard.SaveCreditCardEventSubscription;
import fr.remy.cc1.domain.invoice.SubscriptionSuccessfulEventInvoiceSubscription;
import fr.remy.cc1.domain.mail.RegisterUserEventMessengerSubscription;
import fr.remy.cc1.infrastructure.mail.SandboxMail;
import fr.remy.cc1.domain.mail.SubscriptionSuccessfulEventMessengerSubscription;
import fr.remy.cc1.domain.customer.SubscriptionSuccessfulEventCustomerSubscription;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class UserCreationStub {

    public static void initUserCreationTest(Users users, Invoices invoices, CreditCards creditCards) {
        buildStubMailSender();
        buildStubEventBus(users, invoices, creditCards);
    }

    private static void buildStubMailSender() {
        MockEmailSender.getInstance().resetMockEmailSenderInstance();
        MockEmailSender mockEmailSender = MockEmailSender.getInstance();
        mockEmailSender.setMail(new SandboxMail());
    }

    private static void buildStubEventBus(Users users, Invoices invoices, CreditCards creditCards) {
        MockEmailSender mockEmailSender = MockEmailSender.getInstance();

        List<Subscriber> subscriptionSuccessfulEventSubscriptions = Arrays.asList(
                new SubscriptionSuccessfulEventMessengerSubscription(mockEmailSender),
                new SubscriptionSuccessfulEventInvoiceSubscription(invoices),
                new SubscriptionSuccessfulEventCustomerSubscription(users)
        );

        Map<Class, List<Subscriber>> subscriptionMap = Map.of(
                RegisterUserEvent.class, Collections.singletonList(new RegisterUserEventMessengerSubscription(mockEmailSender)),
                SaveCreditCardEvent.class, Collections.singletonList(new SaveCreditCardEventSubscription(creditCards)),
                SubscriptionSuccessfulEvent.class, Collections.unmodifiableList(subscriptionSuccessfulEventSubscriptions)
        );

        UserCreationEventBus userCreationEventBus = UserCreationEventBus.getInstance();
        userCreationEventBus.setSubscribers(subscriptionMap);
    }
}
