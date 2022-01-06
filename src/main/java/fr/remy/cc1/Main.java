package fr.remy.cc1;

import fr.remy.cc1.application.customer.SubscriptionSuccessfulEvent;
import fr.remy.cc1.application.customer.SubscriptionSuccessfulEventCustomerSubscription;
import fr.remy.cc1.application.payment.SaveCreditCardEvent;
import fr.remy.cc1.application.payment.SaveCreditCardEventSubscription;
import fr.remy.cc1.application.user.RegisterUserEvent;
import fr.remy.cc1.domain.customer.*;
import fr.remy.cc1.domain.payment.Currency;
import fr.remy.cc1.kernel.error.ValidationException;
import fr.remy.cc1.kernel.event.Subscriber;
import fr.remy.cc1.domain.invoice.Invoices;
import fr.remy.cc1.domain.payment.*;
import fr.remy.cc1.domain.payment.creditcard.*;
import fr.remy.cc1.domain.user.*;
import fr.remy.cc1.infrastructure.creditcards.InMemoryCreditCards;
import fr.remy.cc1.infrastructure.invoices.InMemoryInvoices;
import fr.remy.cc1.application.invoice.SubscriptionSuccessfulEventInvoiceSubscription;
import fr.remy.cc1.domain.mail.EmailSender;
import fr.remy.cc1.application.mail.RegisterUserEventMessengerSubscription;
import fr.remy.cc1.infrastructure.mail.SandboxMail;
import fr.remy.cc1.application.mail.SubscriptionSuccessfulEventMessengerSubscription;
import fr.remy.cc1.infrastructure.user.InMemoryUsers;
import fr.remy.cc1.infrastructure.user.UserCreationEventBus;

import java.math.BigDecimal;
import java.util.*;

public class Main {

    public static void main(String[] args) throws ValidationException {

        EmailSender emailSender = EmailSender.getInstance();
        emailSender.setMail(new SandboxMail());

        Users users = new InMemoryUsers();
        Invoices invoices = new InMemoryInvoices();
        CreditCards creditCards = new InMemoryCreditCards();
        CreditCardService creditCardService = new CreditCardService(creditCards);

        Map<Class, List<Subscriber>> subscriptionMap = initSubscriptionMap(emailSender, invoices, users, creditCardService);
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
        String userCategoryChoiceStub = "Trademan";
        UserCategoryCreator userCategoryCreator = new UserCategoryCreator();
        boolean saveCreditCardStub = true;

        CurrencyValidator.getInstance().test(currencyChoiceStub);

        SubscriptionOffer subscriptionOffer = SubscriptionOffer.of(new Money(priceSubscriptionOfferStub, Currency.valueOf(currencyChoiceStub)),discountPercentageStub);

        final UserId myUserId = users.nextIdentity();
        User user = User.of(myUserId, lastnameStub, firstnameStub, emailStub, passwordStub, userCategoryCreator.getValueOf(userCategoryChoiceStub));


        final CreditCardId creditCardId = creditCards.nextIdentity();
        CreditCard creditCard = CreditCard.of(creditCardId,"1234567262", 1203, 321, "POMME", user.getUserId());
        Payer payer = new Payer(creditCard, null);

        createUser(user, users, payer, paymentChoiceStub, saveCreditCardStub, subscriptionOffer);
    }

    private static void createUser(
            User user,
            Users users,
            Payer payer,
            String paymentMethod,
            boolean saveCreditCard,
            SubscriptionOffer subscriptionOffer
    ) {
        Payment payment = getPayment(paymentMethod, payer);

        UserService userService = new UserService(users);
        PaymentService paymentService = new PaymentService(payment);

        paymentService.paySubscription(subscriptionOffer, user);
        userService.create(user);
        if(saveCreditCard)
            UserCreationEventBus.getInstance().send(SaveCreditCardEvent.of(payer.getCreditCard(), user));
    }

    private static Map<Class, List<Subscriber>> initSubscriptionMap(EmailSender emailSender, Invoices invoices, Users users, CreditCardService creditCardService) {
        List<Subscriber> subscriptionSuccessfulEventSubscriptions = Arrays.asList(
                new SubscriptionSuccessfulEventMessengerSubscription(emailSender),
                new SubscriptionSuccessfulEventInvoiceSubscription(invoices, users),
                new SubscriptionSuccessfulEventCustomerSubscription(users)
        );

        return Map.of(
                RegisterUserEvent.class, Collections.singletonList(new RegisterUserEventMessengerSubscription(emailSender)),
                SaveCreditCardEvent.class, Collections.singletonList(new SaveCreditCardEventSubscription(creditCardService)),
                SubscriptionSuccessfulEvent.class, Collections.unmodifiableList(subscriptionSuccessfulEventSubscriptions)
        );
    }

    private static Payment getPayment(String paymentMethod, Payer payer) {
        if(!PaymentMethodValidator.getInstance().test(paymentMethod)) {
            throw new IllegalArgumentException(PaymentMethodValidator.exceptionMessage);
        }
        if(paymentMethod.equals(Payer.PAYMENT_METHOD_SUPPORTED.get(0))) {
            return PaymentDirector.createPaypalPayment(payer.getPaypalAccount());
        }else if(paymentMethod.equals(Payer.PAYMENT_METHOD_SUPPORTED.get(1))) {
            return PaymentDirector.createCreditCardPayment(payer.getCreditCard(), PaymentCreditCardHandlerCreator.buildPaymentHandlers(
                    List.of(new CreditCardChecker(), new CreditCardApproveTradesman(), new CreditCardContractor())
            ));
        }

        throw new IllegalArgumentException(PaymentMethodValidator.exceptionMessage);
    }
}
