package fr.remy.cc1;

import fr.remy.cc1.application.*;
import fr.remy.cc1.application.customer.SubscriptionPaymentFailedEvent;
import fr.remy.cc1.application.customer.SubscriptionSuccessfulEvent;
import fr.remy.cc1.application.invoice.SubscriptionPaymentFailedEventInvoiceSubscription;
import fr.remy.cc1.application.invoice.SubscriptionSuccessfulEventInvoiceSubscription;
import fr.remy.cc1.application.mail.RegisterUserEventMessengerSubscription;
import fr.remy.cc1.application.mail.SubscriptionPaymentFailedEventMessengerSubscription;
import fr.remy.cc1.application.mail.SubscriptionSuccessfulEventMessengerSubscription;
import fr.remy.cc1.application.user.RegisterUserEvent;
import fr.remy.cc1.domain.invoice.Invoices;
import fr.remy.cc1.domain.mail.EmailSender;
import fr.remy.cc1.domain.payment.creditcard.CreditCards;
import fr.remy.cc1.domain.payment.paypal.PaypalAccounts;
import fr.remy.cc1.domain.user.UserCategoryCreator;
import fr.remy.cc1.domain.user.UserId;
import fr.remy.cc1.domain.user.Users;
import fr.remy.cc1.infrastructure.creditcards.InMemoryCreditCards;
import fr.remy.cc1.infrastructure.invoices.InMemoryInvoices;
import fr.remy.cc1.infrastructure.mail.SandboxMail;
import fr.remy.cc1.infrastructure.paypalAccounts.InMemoryPaypalAccounts;
import fr.remy.cc1.infrastructure.user.InMemoryUsers;
import fr.remy.cc1.infrastructure.user.UserCreationEventBus;
import fr.remy.cc1.kernel.event.Subscriber;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws Exception {

        EmailSender emailSender = EmailSender.getInstance();
        emailSender.setMail(new SandboxMail());

        Users users = new InMemoryUsers();
        Invoices invoices = new InMemoryInvoices();
        CreditCards creditCards = new InMemoryCreditCards();
        PaypalAccounts paypalAccounts = new InMemoryPaypalAccounts();

        Map<Class, List<Subscriber>> subscriptionMap = initSubscriptionMap(emailSender, invoices, users);
        UserCreationEventBus userCreationEventBus = UserCreationEventBus.getInstance();
        userCreationEventBus.setSubscribers(subscriptionMap);


        String lastnameStub = "Machavoine";
        String firstnameStub = "Rémy";
        String emailStub = "pomme@pomme.fr";
        String passwordStub = "aZertyu9?";
        CreateUserCommandHandler createUserCommandHandler = new CreateUserCommandHandler(users, UserCreationEventBus.getInstance());
        CreatePaymentCommandHandler createPaymentCommandHandler = new CreatePaymentCommandHandler(creditCards, paypalAccounts, users);
        CreateSubscriptionOfferCommandHandler createSubscriptionOfferCommandHandler = new CreateSubscriptionOfferCommandHandler(creditCards, paypalAccounts, users, UserCreationEventBus.getInstance());
        BigDecimal priceSubscriptionOfferStub = new BigDecimal("12.05");
        int discountPercentageStub = 10;

        String currencyChoiceStub = "EUR";
        String paymentChoiceStub = "CreditCard";
        String userCategoryChoiceStub = "TRADESMAN";
        String creditCardNumber = "1234567262";
        int creditCardExpiryDate = 1203;
        int creditCardSecurityCode = 321;
        String creditCardName = "Pomme";

        CreateUser createUser = new CreateUser(lastnameStub, firstnameStub, emailStub, passwordStub, UserCategoryCreator.getValueOf(userCategoryChoiceStub));
        UserId userId = createUserCommandHandler.handle(createUser);

        CreatePayment createPayment = new CreatePayment(
                paymentChoiceStub,
                creditCardNumber,
                creditCardExpiryDate,
                creditCardSecurityCode,
                creditCardName,
                userId
        );
        createPaymentCommandHandler.handle(createPayment);

        CreateSubscriptionOffer createSubscriptionOffer = new CreateSubscriptionOffer(
                discountPercentageStub,
                priceSubscriptionOfferStub,
                userId,
                currencyChoiceStub,
                paymentChoiceStub
        );
        createSubscriptionOfferCommandHandler.handle(createSubscriptionOffer);

    }

    private static Map<Class, List<Subscriber>> initSubscriptionMap(EmailSender emailSender, Invoices invoices, Users users) {

        List<Subscriber> subscriptionSuccessfulEventSubscriptions = Arrays.asList(
                new SubscriptionSuccessfulEventMessengerSubscription(emailSender),
                new SubscriptionSuccessfulEventInvoiceSubscription(invoices, users)
        );

        List<Subscriber> subscriptionPaymentFailedEventSubscriptions = Arrays.asList(
                new SubscriptionPaymentFailedEventMessengerSubscription(emailSender),
                new SubscriptionPaymentFailedEventInvoiceSubscription(invoices, users)
        );

        return Map.of(
                RegisterUserEvent.class, Collections.singletonList(new RegisterUserEventMessengerSubscription(emailSender)),
                SubscriptionSuccessfulEvent.class, Collections.unmodifiableList(subscriptionSuccessfulEventSubscriptions),
                SubscriptionPaymentFailedEvent.class, Collections.unmodifiableList(subscriptionPaymentFailedEventSubscriptions)
        );
    }
}
