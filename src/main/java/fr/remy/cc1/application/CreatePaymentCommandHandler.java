package fr.remy.cc1.application;

import fr.remy.cc1.domain.payment.Payer;
import fr.remy.cc1.domain.payment.PaymentMethodValidator;
import fr.remy.cc1.domain.payment.creditcard.*;
import fr.remy.cc1.domain.payment.paypal.PayPalAccountId;
import fr.remy.cc1.domain.payment.paypal.PaypalAccount;
import fr.remy.cc1.domain.payment.paypal.PaypalAccounts;
import fr.remy.cc1.domain.user.User;
import fr.remy.cc1.domain.user.UserId;
import fr.remy.cc1.domain.user.Users;
import fr.remy.cc1.kernel.CommandHandler;
import fr.remy.cc1.kernel.event.Event;
import fr.remy.cc1.kernel.event.EventBus;

public class CreatePaymentCommandHandler implements CommandHandler<CreatePayment, Void> {

    private final CreditCards creditCards;
    private final PaypalAccounts paypalAccounts;
    private final Users users;
    private final EventBus<Event> eventBus;

    public CreatePaymentCommandHandler(CreditCards creditCards, PaypalAccounts paypalAccounts, Users users, EventBus<Event> eventBus) {
        this.creditCards = creditCards;
        this.paypalAccounts = paypalAccounts;
        this.users = users;
        this.eventBus = eventBus;
    }

    @Override
    public Void handle(CreatePayment command) {

        User user = this.users.byId(command.userId);
        if(!PaymentMethodValidator.getInstance().test(command.payment)) {
            throw new IllegalArgumentException(PaymentMethodValidator.exceptionMessage);
        }

        if(command.payment.equals(Payer.PAYMENT_METHOD_SUPPORTED.get(0))) {
            final PayPalAccountId payPalAccountId = paypalAccounts.nextIdentity();
            try {
                PaypalAccount paypalAccount = this.paypalAccounts.byUserId(user.getUserId());
                this.paypalAccounts.delete(paypalAccount.getPayPalAccountId());
            }catch(Exception e) {}
            PaypalAccount paypalAccount = new PaypalAccount(payPalAccountId, user.getUserId());
            this.paypalAccounts.save(paypalAccount);
        }else if(command.payment.equals(Payer.PAYMENT_METHOD_SUPPORTED.get(1))) {
            final CreditCardId creditCardId = creditCards.nextIdentity();
            try {
                CreditCard creditCard = this.creditCards.byUserId(user.getUserId());
                this.creditCards.delete(creditCard.getCreditCardId());
            }catch(Exception e) {}
            CreditCard creditCard = CreditCard.of(creditCardId, command.creditCardNumber, command.creditCardExpiryDate, command.creditCardSecurityCode, command.creditCardName, user.getUserId());
            this.creditCards.save(creditCard, user);
        }
        return null;
    }
}
