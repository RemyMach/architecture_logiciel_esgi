package fr.remy.cc1.subscription.application.payment;

import fr.remy.cc1.domain.User;
import fr.remy.cc1.kernel.CommandHandler;
import fr.remy.cc1.member.domain.user.Users;
import fr.remy.cc1.subscription.domain.PaymentMethod.PaymentMethod;
import fr.remy.cc1.subscription.domain.PaymentMethod.PaymentMethodCreator;
import fr.remy.cc1.subscription.domain.creditcard.CreditCard;
import fr.remy.cc1.subscription.domain.creditcard.CreditCardId;
import fr.remy.cc1.subscription.domain.creditcard.CreditCards;
import fr.remy.cc1.subscription.domain.paypal.PayPalAccountId;
import fr.remy.cc1.subscription.domain.paypal.PaypalAccount;
import fr.remy.cc1.subscription.domain.paypal.PaypalAccounts;

public class CreatePaymentCommandHandler implements CommandHandler<CreatePayment, Void> {

    private final CreditCards creditCards;
    private final PaypalAccounts paypalAccounts;
    private final Users users;

    public CreatePaymentCommandHandler(CreditCards creditCards, PaypalAccounts paypalAccounts, Users users) {
        this.creditCards = creditCards;
        this.paypalAccounts = paypalAccounts;
        this.users = users;
    }

    @Override
    public Void handle(CreatePayment command) throws Exception {

        User user = this.users.byId(command.userId);
        PaymentMethod paymentMethodEnum = PaymentMethodCreator.getValueOf(command.payment);
        if(paymentMethodEnum == PaymentMethod.Paypal) {

            final PayPalAccountId payPalAccountId = paypalAccounts.nextIdentity();
            try {
                PaypalAccount paypalAccount = this.paypalAccounts.findByUserId(user.getUserId());
                this.paypalAccounts.delete(paypalAccount.getPayPalAccountId());
            }catch(Exception e) {}
            PaypalAccount paypalAccount = new PaypalAccount(payPalAccountId, user.getUserId());
            this.paypalAccounts.save(paypalAccount);

        }else if(paymentMethodEnum == PaymentMethod.CreditCard) {

            final CreditCardId creditCardId = creditCards.nextIdentity();
            try {
                CreditCard creditCard = this.creditCards.findByUserId(user.getUserId());
                this.creditCards.delete(creditCard.getCreditCardId());
            }catch(Exception e) {}
            CreditCard creditCard = CreditCard.of(creditCardId, command.creditCardNumber, command.creditCardExpiryDate, command.creditCardSecurityCode, command.creditCardName, user.getUserId());
            this.creditCards.save(creditCard, user.getUserId());
        }

        return null;
    }
}
