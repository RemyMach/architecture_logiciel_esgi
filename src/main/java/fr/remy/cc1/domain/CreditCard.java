package fr.remy.cc1.domain;

public class CreditCard {

    private final int number;

    private final int expiryDate;

    private final int securityCode;

    private final String name;

    private CreditCard(int number, int expiryDate, int securityCode, String name) {
        this.number = number;
        this.expiryDate = expiryDate;
        this.securityCode = securityCode;
        this.name = name;
    }

    public static CreditCard of(int number, int expiryDate, int securityCode, String name) {
        return new CreditCard(number, expiryDate, securityCode, name);
    }
}
