package fr.remy.cc1.domain.payment;

public class CreditCardContractor extends PaymentCreditCardHandler {

    @Override
    public void process(CreditCard creditCard) {
        System.out.println("je suis dans le contractor");
        checkNext(creditCard);
    }
}
