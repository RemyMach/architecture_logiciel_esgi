package fr.remy.cc1.application;

import fr.remy.cc1.domain.customer.SubscriptionOffer;
import fr.remy.cc1.domain.invoice.Invoice;
import fr.remy.cc1.domain.payment.*;
import fr.remy.cc1.domain.payment.creditcard.*;
import fr.remy.cc1.domain.payment.currency.Currency;
import fr.remy.cc1.domain.payment.currency.CurrencyValidator;
import fr.remy.cc1.domain.payment.paypal.PaypalAccounts;
import fr.remy.cc1.domain.user.User;
import fr.remy.cc1.domain.user.UserService;
import fr.remy.cc1.kernel.CommandHandler;
import fr.remy.cc1.kernel.event.Event;
import fr.remy.cc1.kernel.event.EventBus;

import java.util.ArrayList;
import java.util.List;

public class CreateSubscriptionOfferCommandHandler implements CommandHandler<CreateSubscriptionOffer, Void> {

    private final CreditCards creditCards;
    private final PaypalAccounts paypalAccounts;
    private final UserService userService;
    private final EventBus<Event> eventBus;

    public CreateSubscriptionOfferCommandHandler(CreditCards creditCards, PaypalAccounts paypalAccounts, UserService userService, EventBus<Event> eventBus) {
        this.creditCards = creditCards;
        this.paypalAccounts = paypalAccounts;
        this.userService = userService;
        this.eventBus = eventBus;
    }

    @Override
    public Void handle(CreateSubscriptionOffer command) throws Exception {
        User user = this.userService.getUser(command.userId);

        if(!PaymentMethodValidator.getInstance().test(command.payment)) {
            throw new IllegalArgumentException(PaymentMethodValidator.exceptionMessage);
        }
        Payment payment = null;
        if(command.payment.equals(Payer.PAYMENT_METHOD_SUPPORTED.get(0))) {
            payment = PaymentDirector.createPaypalPayment(paypalAccounts.findByUserId(user.getUserId()));
        }else if(command.payment.equals(Payer.PAYMENT_METHOD_SUPPORTED.get(1))) {
            payment = PaymentDirector.createCreditCardPayment(creditCards.findByUserId(user.getUserId()), PaymentCreditCardHandlerCreator.buildPaymentHandlers(
                    List.of(new CreditCardValidityMiddleware(), new CreditCardValidityTradeMiddleware(), new CreditCardBankAccountValidityMiddleware()))
            );
        }
        SubscriptionOffer subscriptionOffer = SubscriptionOffer.of(new Money(command.amount, Currency.valueOf(command.currency)), command.discountPercentage, new ArrayList<Invoice>());
        CurrencyValidator.getInstance().test(command.currency);

        PaymentService paymentService = new PaymentService(payment);
        paymentService.paySubscription(subscriptionOffer, user);
        this.userService.create(user);

        return null;
    }
}
