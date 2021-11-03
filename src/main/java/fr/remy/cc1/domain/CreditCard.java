package fr.remy.cc1.domain;

public class CreditCard {

    private final int number;

    private final int expiryDate;

    private final int securityCode;

    private final String name;

    private final User user;

    private CreditCard(int number, int expiryDate, int securityCode, String name, User user) {
        this.number = number;
        this.expiryDate = expiryDate;
        this.securityCode = securityCode;
        this.name = name;
        this.user = user;
    }

    public static CreditCard of(int number, int expiryDate, int securityCode, String name, User user) {
        return new CreditCard(number, expiryDate, securityCode, name, user);
    }
}
