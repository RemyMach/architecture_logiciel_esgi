package fr.remy.cc1.domain.payment;

public class Contractor extends PaymentHandler{

    @Override
    public boolean process() {
        System.out.println("je suis dans le contractor");
        return checkNext();
    }
}
