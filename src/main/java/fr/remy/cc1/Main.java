package fr.remy.cc1;

import fr.remy.cc1.domain.*;
import fr.remy.cc1.domain.event.Subscriber;
import fr.remy.cc1.domain.payment.*;
import fr.remy.cc1.domain.user.User;
import fr.remy.cc1.domain.user.UserId;
import fr.remy.cc1.domain.user.UserService;
import fr.remy.cc1.domain.user.Users;
import fr.remy.cc1.infrastructure.*;
import fr.remy.cc1.infrastructure.creditcards.InMemoryCreditCards;
import fr.remy.cc1.infrastructure.creditcards.SaveCreditCardEvent;
import fr.remy.cc1.infrastructure.creditcards.SaveCreditCardEventSubscription;
import fr.remy.cc1.infrastructure.invoices.InMemoryInvoices;
import fr.remy.cc1.infrastructure.invoices.SubscriptionSuccessfulEventInvoiceSubscription;
import fr.remy.cc1.infrastructure.mail.EmailSender;
import fr.remy.cc1.infrastructure.mail.RegisterUserEventMessengerSubscription;
import fr.remy.cc1.infrastructure.mail.SandboxMail;
import fr.remy.cc1.infrastructure.mail.SubscriptionSuccessfulEventMessengerSubscription;
import fr.remy.cc1.infrastructure.users.InMemoryUsers;
import fr.remy.cc1.infrastructure.users.SubscriptionSuccessfulEventUserSubscription;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        EmailSender emailSender = EmailSender.getInstance();
        emailSender.setMail(new SandboxMail());

        Users users = new InMemoryUsers();
        Invoices invoices = new InMemoryInvoices();
        CreditCards creditCards = new InMemoryCreditCards();

        Map<Class, List<Subscriber>> subscriptionMap = initSubscriptionMap(emailSender, invoices, users, creditCards);
        UserCreationEventBus userCreationEventBus = UserCreationEventBus.getInstance();
        userCreationEventBus.setSubscribers(subscriptionMap);


        String lastnameStub = "Machavoine";
        String firstnameStub = "Rémy";
        String emailStub = "pomme@pomme.fr";
        String passwordStub = "aZertyu9?";

        BigDecimal priceSubscriptionOfferStub = new BigDecimal("12.05");
        int discountPercentageStub = 10;

        String currencyChoiceStub = "EUR";
        String paymentChoiceStub = "CreditCard";
        boolean saveCreditCardStub = true;

        SubscriptionOffer subscriptionOffer = SubscriptionOffer.of(priceSubscriptionOfferStub,discountPercentageStub);

        final UserId myUserId = users.nextIdentity();
        User user = User.of(myUserId, lastnameStub, firstnameStub, emailStub, passwordStub);

        CurrencyValidator.getInstance().test(currencyChoiceStub);

        final CreditCardId creditCardId = creditCards.nextIdentity();
        CreditCard creditCard = CreditCard.of(creditCardId,1234567262, 1203, 321, "POMME");
        Payor payor = new Payor(creditCard, null);

        createUser(user, users, payor, currencyChoiceStub, paymentChoiceStub, saveCreditCardStub, subscriptionOffer);
    }

    private static void createUser(
            User user,
            Users users,
            Payor payor,
            String currency,
            String paymentMethod,
            boolean saveCreditCard,
            SubscriptionOffer subscriptionOffer
    ) {

        PaymentBuild paymentBuild = new PaymentBuild();
        Payment payment = paymentBuild.getPaymentOf(paymentMethod, payor);

        UserService userService = new UserService(users);
        PaymentService paymentService = new PaymentService(payment);

        paymentService.paySubscription(subscriptionOffer,  Currency.valueOf(currency), user);
        userService.create(user);
        if(saveCreditCard)
            UserCreationEventBus.getInstance().send(SaveCreditCardEvent.of(payor.getCreditCard(), user));
    }

    private static Map<Class, List<Subscriber>> initSubscriptionMap(EmailSender emailSender, Invoices invoices, Users users, CreditCards creditCards) {
        List<Subscriber> subscriptionSuccessfulEventSubscriptions = Arrays.asList(
                new SubscriptionSuccessfulEventMessengerSubscription(emailSender),
                new SubscriptionSuccessfulEventInvoiceSubscription(invoices),
                new SubscriptionSuccessfulEventUserSubscription(users)
        );

        return Map.of(
                RegisterUserEvent.class, Collections.singletonList(new RegisterUserEventMessengerSubscription(emailSender)),
                SaveCreditCardEvent.class, Collections.singletonList(new SaveCreditCardEventSubscription(creditCards)),
                SubscriptionSuccessfulEvent.class, Collections.unmodifiableList(subscriptionSuccessfulEventSubscriptions)
        );
    }
}
