package fr.remy.cc1.exposition.subscription;

import javax.validation.constraints.NotNull;

// Getter and setter mandatory for Spring validation
public class CardRequest {

    @NotNull(message="userId_empty_null")
    public boolean saveCreditCard;

    @NotNull(message="userId_empty_null")
    public String creditCardNumber;

    @NotNull(message="userId_empty_null")
    public Integer creditCardExpiryDate;

    @NotNull(message="userId_empty_null")
    public Integer creditCardSecurityCode;

    @NotNull(message="userId_empty_null")
    public String creditCardName;

    public boolean isSaveCreditCard() {
        return saveCreditCard;
    }

    public void setSaveCreditCard(boolean saveCreditCard) {
        this.saveCreditCard = saveCreditCard;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public Integer getCreditCardExpiryDate() {
        return creditCardExpiryDate;
    }

    public void setCreditCardExpiryDate(Integer creditCardExpiryDate) {
        this.creditCardExpiryDate = creditCardExpiryDate;
    }

    public Integer getCreditCardSecurityCode() {
        return creditCardSecurityCode;
    }

    public void setCreditCardSecurityCode(Integer creditCardSecurityCode) {
        this.creditCardSecurityCode = creditCardSecurityCode;
    }

    public String getCreditCardName() {
        return creditCardName;
    }

    public void setCreditCardName(String creditCardName) {
        this.creditCardName = creditCardName;
    }
}
