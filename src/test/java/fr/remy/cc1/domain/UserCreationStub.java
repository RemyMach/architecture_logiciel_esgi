package fr.remy.cc1.domain;

import fr.remy.cc1.domain.payment.creditcard.CreditCardService;
import fr.remy.cc1.kernel.event.Subscriber;
import fr.remy.cc1.domain.mail.MockEmailSender;
import fr.remy.cc1.domain.invoice.Invoices;
import fr.remy.cc1.domain.user.Users;
import fr.remy.cc1.application.user.RegisterUserEvent;
import fr.remy.cc1.application.customer.SubscriptionSuccessfulEvent;
import fr.remy.cc1.infrastructure.user.UserCreationEventBus;
import fr.remy.cc1.application.payment.SaveCreditCardEvent;
import fr.remy.cc1.application.payment.SaveCreditCardEventSubscription;
import fr.remy.cc1.application.invoice.SubscriptionSuccessfulEventInvoiceSubscription;
import fr.remy.cc1.domain.mail.RegisterUserEventMessengerSubscription;
import fr.remy.cc1.infrastructure.mail.SandboxMail;
import fr.remy.cc1.domain.mail.SubscriptionSuccessfulEventMessengerSubscription;
import fr.remy.cc1.application.customer.SubscriptionSuccessfulEventCustomerSubscription;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class UserCreationStub {

    public static void initUserCreationTest(Users users, Invoices invoices, CreditCardService creditCardService) {
        buildStubMailSender();
        buildStubEventBus(users, invoices, creditCardService);
    }

    private static void buildStubMailSender() {
        MockEmailSender.getInstance().resetMockEmailSenderInstance();
        MockEmailSender mockEmailSender = MockEmailSender.getInstance();
        mockEmailSender.setMail(new SandboxMail());
    }

    private static void buildStubEventBus(Users users, Invoices invoices, CreditCardService creditCardService) {
        MockEmailSender mockEmailSender = MockEmailSender.getInstance();

        List<Subscriber> subscriptionSuccessfulEventSubscriptions = Arrays.asList(
                new SubscriptionSuccessfulEventMessengerSubscription(mockEmailSender),
                new SubscriptionSuccessfulEventInvoiceSubscription(invoices),
                new SubscriptionSuccessfulEventCustomerSubscription(users)
        );

        Map<Class, List<Subscriber>> subscriptionMap = Map.of(
                RegisterUserEvent.class, Collections.singletonList(new RegisterUserEventMessengerSubscription(mockEmailSender)),
                SaveCreditCardEvent.class, Collections.singletonList(new SaveCreditCardEventSubscription(creditCardService)),
                SubscriptionSuccessfulEvent.class, Collections.unmodifiableList(subscriptionSuccessfulEventSubscriptions)
        );

        UserCreationEventBus userCreationEventBus = UserCreationEventBus.getInstance();
        userCreationEventBus.setSubscribers(subscriptionMap);
    }
}
