package fr.remy.cc1.domain.payment.creditcard;

public class CreditCardChecker extends PaymentCreditCardHandler {

    @Override
    public void process(CreditCard creditCard) {
        System.out.println("je suis dans le payment process");
        checkNext(creditCard);
    }
}
