package fr.remy.cc1.application;

import fr.remy.cc1.domain.customer.SubscriptionOffer;
import fr.remy.cc1.domain.payment.*;
import fr.remy.cc1.domain.payment.creditcard.*;
import fr.remy.cc1.domain.user.User;
import fr.remy.cc1.domain.user.UserId;
import fr.remy.cc1.domain.user.UserService;
import fr.remy.cc1.domain.user.Users;
import fr.remy.cc1.infrastructure.user.UserCreationEventBus;
import fr.remy.cc1.kernel.CommandHandler;
import fr.remy.cc1.kernel.error.ValidationException;
import fr.remy.cc1.kernel.event.Event;
import fr.remy.cc1.kernel.event.EventBus;

import java.util.List;

public class CreateSubscriptionOfferCommandHandler implements CommandHandler<CreateSubscriptionOffer, Void> {

    private final CreditCards creditCards;
    private final UserService userService;
    private final EventBus<Event> eventBus;

    public CreateSubscriptionOfferCommandHandler(CreditCards creditCards, UserService userService, EventBus<Event> eventBus) {
        this.creditCards = creditCards;
        this.userService = userService;
        this.eventBus = eventBus;
    }

    @Override
    public Void handle(CreateSubscriptionOffer command) {
        SubscriptionOffer subscriptionOffer = SubscriptionOffer.of(command.amount, command.discountPercentage);
        User user = this.userService.getUser(UserId.of(command.userId));
        CurrencyValidator.getInstance().test(command.currency);

        final CreditCardId creditCardId = creditCards.nextIdentity();
        CreditCard creditCard = CreditCard.of(creditCardId,command.creditCardNumber, command.creditCardExpiryDate, command.creditCardSecurityCode, command.creditCardName);
        Payer payer = new Payer(creditCard, null);

        Payment payment = PaymentCreator.withCreditCard(payer.getCreditCard(), PaymentCreditCardHandlerCreator.buildPaymentHandlers(
                List.of(new CreditCardChecker(), new CreditCardApproveTradesman(), new CreditCardContractor())
        ));
        PaymentService paymentService = new PaymentService(payment);
        paymentService.paySubscription(subscriptionOffer,  Currency.valueOf(command.currency), user);
        this.userService.create(user);
        if(command.saveCreditCard)
            UserCreationEventBus.getInstance().send(SaveCreditCardEvent.of(payer.getCreditCard(), user));

        return null;
    }
}
