package fr.remy.cc1.domain.payment;

public class ApproveTradesman extends PaymentHandler {


    @Override
    public boolean process() {
        System.out.println("je suis dans le approve tradesman");
        return checkNext();
    }
}
