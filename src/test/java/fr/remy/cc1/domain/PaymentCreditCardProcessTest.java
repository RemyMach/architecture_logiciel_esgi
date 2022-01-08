package fr.remy.cc1.domain;

import fr.remy.cc1.domain.customer.SubscriptionOffer;
import fr.remy.cc1.domain.payment.*;
import fr.remy.cc1.domain.payment.creditcard.*;
import fr.remy.cc1.domain.payment.currency.Currency;
import fr.remy.cc1.domain.user.*;
import fr.remy.cc1.infrastructure.creditcards.InMemoryCreditCards;
import fr.remy.cc1.infrastructure.user.InMemoryUsers;
import fr.remy.cc1.kernel.error.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class PaymentCreditCardProcessTest {

    Payment payment;
    CreditCards creditCards;
    CreditCardId creditCardIdStub;
    CreditCard creditCard;

    SubscriptionOffer subscriptionOffer;
    Payer payer;
    MockCreditCardValidityMiddleware mockCreditCardChecker;
    MockCreditCardValidityTradeMiddleware mockCreditCardApproveTradesman;
    MockCreditCardBankAccountValidityMiddleware mockCreditCardContractor;

    User user;

    @BeforeEach
    void initStubValues() throws ValidationException {
        this.creditCards = new InMemoryCreditCards();
        this.creditCardIdStub = creditCards.nextIdentity();
        Users users = new InMemoryUsers();
        this.user = User.of(users.nextIdentity(), "Machavoine", "Rémy", new Email("pomme@pomme.fr"), new Password("aZertyu9?"), UserCategoryCreator.getValueOf("TRADESMAN"));
        this.creditCard = CreditCard.of(this.creditCardIdStub, "1234567262", 1203, 321, "POMME", user.getUserId());
        this.mockCreditCardChecker = new MockCreditCardValidityMiddleware();
        this.mockCreditCardApproveTradesman = new MockCreditCardValidityTradeMiddleware();
        this.mockCreditCardContractor = new MockCreditCardBankAccountValidityMiddleware();
        this.subscriptionOffer = SubscriptionOffer.of(new Money(new BigDecimal("10"), Currency.EUR), 10);
        this.payer = new Payer(creditCard, null);
    }

    @Test
    @DisplayName("should work with all the payment handler called")
    public void threePaymentHandlerAreCalled() {

        PaymentCardMiddleware paymentCreditCardHandler =
                PaymentCreditCardHandlerCreator.buildPaymentHandlers(
                        List.of(this.mockCreditCardChecker, this.mockCreditCardApproveTradesman, this.mockCreditCardContractor));
        this.payment = new StubCreditCardPayment(this.creditCard, paymentCreditCardHandler);

        assertEquals(this.mockCreditCardChecker.getCountProcess(), 0);
        assertEquals(this.mockCreditCardApproveTradesman.getCountProcess(), 0);
        assertEquals(this.mockCreditCardContractor.getCountProcess(), 0);

        try {

            this.payment.start(subscriptionOffer.getMoney());
        }catch(Exception exception) {
            fail( "Should not thrown an exception" );
        }

        assertEquals(this.mockCreditCardChecker.getCountProcess(), 1);
        assertEquals(this.mockCreditCardApproveTradesman.getCountProcess(), 1);
        assertEquals(this.mockCreditCardContractor.getCountProcess(), 1);
    }

    @Test
    @DisplayName("should work with only creditCardChecker called")
    public void everyPaymentHandlerAreCalled() {
        PaymentCardMiddleware paymentCreditCardHandler =
                PaymentCreditCardHandlerCreator.buildPaymentHandlers(
                        List.of(this.mockCreditCardChecker));
        this.payment = new StubCreditCardPayment(this.creditCard, paymentCreditCardHandler);

        assertEquals(this.mockCreditCardChecker.getCountProcess(), 0);
        assertEquals(this.mockCreditCardApproveTradesman.getCountProcess(), 0);
        assertEquals(this.mockCreditCardContractor.getCountProcess(), 0);

        try {

            this.payment.start(subscriptionOffer.getMoney());
        }catch(Exception exception) {
            fail( "Should not thrown an exception" );
        }

        assertEquals(this.mockCreditCardChecker.getCountProcess(), 1);
        assertEquals(this.mockCreditCardApproveTradesman.getCountProcess(), 0);
        assertEquals(this.mockCreditCardContractor.getCountProcess(), 0);
    }

    @Test
    @DisplayName("should fail because security code is 420")
    public void securityCodeIsNotValidTest() {

        this.creditCard = CreditCard.of(this.creditCardIdStub, "1234567262", 1203, 420, "POMME", this.user.getUserId());
        this.payment = PaymentDirector.createCreditCardPayment(this.creditCard, PaymentCreditCardHandlerCreator.buildPaymentHandlers(
                List.of(new CreditCardValidityMiddleware(), new CreditCardValidityTradeMiddleware(), new CreditCardBankAccountValidityMiddleware())
        ));

        try {

            this.payment.start(subscriptionOffer.getMoney());
            fail( "Should throw an exception" );
        }catch(IllegalArgumentException illegalArgumentException) {
            assertEquals(illegalArgumentException.getMessage(), "The security code is not valid");
        }
    }
}
