package fr.remy.cc1.domain;

import fr.remy.cc1.domain.customer.SubscriptionOffer;
import fr.remy.cc1.domain.payment.Payer;
import fr.remy.cc1.domain.payment.Payment;
import fr.remy.cc1.domain.payment.creditcard.*;
import fr.remy.cc1.infrastructure.creditcards.InMemoryCreditCards;
import fr.remy.cc1.infrastructure.payment.CreditCardPayment;
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
    MockCreditCardChecker mockCreditCardChecker;
    MockCreditCardApproveTradesman mockCreditCardApproveTradesman;
    MockCreditCardContractor mockCreditCardContractor;

    @BeforeEach
    void initStubValues() {
        this.creditCards = new InMemoryCreditCards();
        this.creditCardIdStub = creditCards.nextIdentity();
        this.creditCard = CreditCard.of(this.creditCardIdStub, 1234567262, 1203, 321, "POMME");
        this.mockCreditCardChecker = new MockCreditCardChecker();
        this.mockCreditCardApproveTradesman = new MockCreditCardApproveTradesman();
        this.mockCreditCardContractor = new MockCreditCardContractor();
        this.subscriptionOffer = SubscriptionOffer.of(new BigDecimal("10"), 10);
        this.payer = new Payer(creditCard, null);
    }

    @Test
    @DisplayName("should work with all the payment handler called")
    public void threePaymentHandlerAreCalled() {

        PaymentCreditCardHandler paymentCreditCardHandler =
                PaymentCreditCardHandlerBuild.buildPaymentHandlers(
                        List.of(this.mockCreditCardChecker, this.mockCreditCardApproveTradesman, this.mockCreditCardContractor));
        this.payment = new StubCreditCardPayment(this.creditCard, paymentCreditCardHandler);

        assertEquals(this.mockCreditCardChecker.getCountProcess(), 0);
        assertEquals(this.mockCreditCardApproveTradesman.getCountProcess(), 0);
        assertEquals(this.mockCreditCardContractor.getCountProcess(), 0);

        try {

            this.payment.start(subscriptionOffer.getAmount());
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
        PaymentCreditCardHandler paymentCreditCardHandler =
                PaymentCreditCardHandlerBuild.buildPaymentHandlers(
                        List.of(this.mockCreditCardChecker));
        this.payment = new StubCreditCardPayment(this.creditCard, paymentCreditCardHandler);

        assertEquals(this.mockCreditCardChecker.getCountProcess(), 0);
        assertEquals(this.mockCreditCardApproveTradesman.getCountProcess(), 0);
        assertEquals(this.mockCreditCardContractor.getCountProcess(), 0);

        try {

            this.payment.start(subscriptionOffer.getAmount());
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

        this.creditCard = CreditCard.of(this.creditCardIdStub, 1234567262, 1203, 420, "POMME");
        PaymentCreditCardHandler paymentCreditCardHandler =
                PaymentCreditCardHandlerBuild.buildPaymentHandlers(
                        List.of(this.mockCreditCardChecker, this.mockCreditCardApproveTradesman, this.mockCreditCardContractor));
        this.payment = new CreditCardPayment(this.creditCard);

        try {

            this.payment.start(subscriptionOffer.getAmount());
            fail( "Should throw an exception" );
        }catch(IllegalArgumentException illegalArgumentException) {
            assertEquals(illegalArgumentException.getMessage(), "The security code is not valid");
        }
    }
}
