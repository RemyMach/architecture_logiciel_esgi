package fr.remy.cc1.exposition;

import java.math.BigDecimal;

public class PaymentRequest {

    public String currency;

    public String payment;

    public BigDecimal amount;

    public boolean saveCreditCard;

    public String creditCardNumber;

    public int creditCardExpiryDate;

    public int creditCardSecurityCode;

    public String creditCardName;
}
