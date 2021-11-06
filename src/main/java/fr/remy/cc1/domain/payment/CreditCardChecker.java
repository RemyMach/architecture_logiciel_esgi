package fr.remy.cc1.domain.payment;

public class CreditCardChecker extends PaymentHandler{

    @Override
    public boolean process() {
        System.out.println("je suis dans le payment process");
        return checkNext();
    }
}
