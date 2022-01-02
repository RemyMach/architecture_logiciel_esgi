package fr.remy.cc1.application;

import fr.remy.cc1.kernel.Command;

public class CreatePayment implements Command {

    public final String payment;

    public final String creditCardNumber;

    public final int creditCardExpiryDate;

    public final int creditCardSecurityCode;

    public final String creditCardName;

    public final int userId;

    public CreatePayment(String payment, String creditCardNumber, int creditCardExpiryDate, int creditCardSecurityCode, String creditCardName, int userId) {
        this.payment = payment;
        this.creditCardNumber = creditCardNumber;
        this.creditCardExpiryDate = creditCardExpiryDate;
        this.creditCardSecurityCode = creditCardSecurityCode;
        this.creditCardName = creditCardName;
        this.userId = userId;
    }
}
