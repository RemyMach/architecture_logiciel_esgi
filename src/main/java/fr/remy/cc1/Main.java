package fr.remy.cc1;

import fr.remy.cc1.kernel.event.Subscriber;
import fr.remy.cc1.member.application.CreateTradesman;
import fr.remy.cc1.member.application.CreateTradesmanCommandHandler;
import fr.remy.cc1.member.application.RegisteredTradesmanEvent;
import fr.remy.cc1.member.application.RegisteredTradesmanEventMessengerSubscription;
import fr.remy.cc1.member.domain.user.Tradesman.Tradesmans;
import fr.remy.cc1.member.domain.user.Users;
import fr.remy.cc1.member.infrastructure.tradesman.InMemoryTradesmans;
import fr.remy.cc1.member.infrastructure.user.InMemoryUsers;
import fr.remy.cc1.member.infrastructure.user.UserCreationEventBus;
import fr.remy.cc1.shared.domain.UserId;
import fr.remy.cc1.shared.domain.mail.EmailSender;
import fr.remy.cc1.shared.infrastructure.InMemory.SubscriptionInvoiceData;
import fr.remy.cc1.shared.infrastructure.InMemory.UserSubscriptionsData;
import fr.remy.cc1.shared.infrastructure.InMemory.UsersData;
import fr.remy.cc1.shared.infrastructure.mail.SandboxMail;
import fr.remy.cc1.subscription.application.*;
import fr.remy.cc1.subscription.application.invoice.SubscriptionPaymentFailedEventInvoiceSubscription;
import fr.remy.cc1.subscription.application.invoice.SubscriptionSuccessTerminatedEventInvoiceSubscription;
import fr.remy.cc1.subscription.application.payment.CreatePayment;
import fr.remy.cc1.subscription.application.payment.CreatePaymentCommandHandler;
import fr.remy.cc1.subscription.domain.SubscriptionOffers;
import fr.remy.cc1.subscription.domain.creditcard.CreditCards;
import fr.remy.cc1.subscription.domain.invoice.Invoices;
import fr.remy.cc1.subscription.domain.paypal.PaypalAccounts;
import fr.remy.cc1.subscription.infrastructure.creditcards.InMemoryCreditCards;
import fr.remy.cc1.subscription.infrastructure.invoices.InMemoryInvoices;
import fr.remy.cc1.subscription.infrastructure.paypalAccounts.InMemoryPaypalAccounts;
import fr.remy.cc1.subscription.infrastructure.subscriptions.InMemorySubscriptionOffers;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Main {

    public static void main(String[] args) throws Exception {

        EmailSender emailSender = EmailSender.getInstance();
        emailSender.setMail(new SandboxMail());

        Tradesmans tradesmans = new InMemoryTradesmans(new ConcurrentHashMap<>());

        SubscriptionInvoiceData.setup(new ConcurrentHashMap<>());
        UserSubscriptionsData.setup(new ConcurrentHashMap<>());
        UsersData.setup(new ConcurrentHashMap<>());
        Users users = new InMemoryUsers(UsersData.getInstance().data, UserSubscriptionsData.getInstance().data);
        SubscriptionOffers subscriptionOffers = new InMemorySubscriptionOffers(new ConcurrentHashMap<>(), UserSubscriptionsData.getInstance().data, SubscriptionInvoiceData.getInstance().data);
        Invoices invoices = new InMemoryInvoices(SubscriptionInvoiceData.getInstance().data);

        CreditCards creditCards = new InMemoryCreditCards();
        PaypalAccounts paypalAccounts = new InMemoryPaypalAccounts();

        Map<Class, List<Subscriber>> subscriptionMap = initSubscriptionMap(emailSender, invoices, users);
        UserCreationEventBus userCreationEventBus = UserCreationEventBus.getInstance();
        userCreationEventBus.setSubscribers(subscriptionMap);


        String lastnameStub = "Machavoine";
        String firstnameStub = "R??my";
        String emailStub = "pomme@pomme.fr";
        String passwordStub = "aZertyu9?";
        CreatePaymentCommandHandler createPaymentCommandHandler = new CreatePaymentCommandHandler(creditCards, paypalAccounts, users);
        CreateSubscriptionOfferCommandHandler createSubscriptionOfferCommandHandler = new CreateSubscriptionOfferCommandHandler(creditCards, paypalAccounts, subscriptionOffers, users, UserCreationEventBus.getInstance());
        CreateTradesmanCommandHandler createTradesmanCommandHandler = new CreateTradesmanCommandHandler(users, tradesmans, UserCreationEventBus.getInstance());
        BigDecimal priceSubscriptionOfferStub = new BigDecimal("12.05");
        int discountPercentageStub = 10;

        String currencyChoiceStub = "EUR";
        String paymentChoiceStub = "CreditCard";
        String userCategoryChoiceStub = "TRADESMAN";
        String creditCardNumber = "1234567262";
        int creditCardExpiryDate = 1203;
        int creditCardSecurityCode = 321;
        String creditCardName = "Pomme";

        CreateTradesman createUser = new CreateTradesman(lastnameStub, firstnameStub, emailStub, passwordStub);
        UserId userId = createTradesmanCommandHandler.handle(createUser);

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
                new SubscriptionPaymentSuccessTerminatedEventMessengerSubscription(emailSender),
                new SubscriptionSuccessTerminatedEventInvoiceSubscription(invoices, users)
        );

        List<Subscriber> subscriptionPaymentFailedEventSubscriptions = Arrays.asList(
                new SubscriptionPaymentFailedEventMessengerSubscription(emailSender),
                new SubscriptionPaymentFailedEventInvoiceSubscription(invoices, users)
        );

        return Map.of(
                RegisteredTradesmanEvent.class, Collections.singletonList(new RegisteredTradesmanEventMessengerSubscription(emailSender)),
                SubscriptionPaymentSuccessTerminatedEvent.class, Collections.unmodifiableList(subscriptionSuccessfulEventSubscriptions),
                SubscriptionPaymentFailedEvent.class, Collections.unmodifiableList(subscriptionPaymentFailedEventSubscriptions)
        );
    }
}
