package fr.remy.cc1.domain.payment.creditcard;

public class CreditCardApproveTradesman extends PaymentCreditCardHandler {

    @Override
    public void process(CreditCard creditCard) {
        System.out.println("je suis dans le approve tradesman");
        checkNext(creditCard);
    }
}
