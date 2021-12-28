package fr.remy.cc1.application;
import fr.remy.cc1.kernel.Command;

import java.math.BigDecimal;

public class CreateSubscriptionOffer implements Command {

    public final int discountPercentage;

    public final BigDecimal amount;

    public final int userId;

    public final String currency;

    public final String payment;

    public final boolean saveCreditCard;

    public final String creditCardNumber;

    public final int creditCardExpiryDate;

    public final int creditCardSecurityCode;

    public final String creditCardName;

    //TODO add a builder
    public CreateSubscriptionOffer(int discountPercentage, BigDecimal amount, int userId, String currency, String payment, boolean saveCreditCard, String creditCardNumber, int creditCardExpiryDate, int creditCardSecurityCode, String creditCardName) {
        this.discountPercentage = discountPercentage;
        this.amount = amount;
        this.userId = userId;
        this.currency = currency;
        this.payment = payment;
        this.saveCreditCard = saveCreditCard;
        this.creditCardNumber = creditCardNumber;
        this.creditCardExpiryDate = creditCardExpiryDate;
        this.creditCardSecurityCode = creditCardSecurityCode;
        this.creditCardName = creditCardName;
    }
}
