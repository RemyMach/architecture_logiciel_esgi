package fr.remy.cc1.exposition.subscription;

import javax.validation.constraints.NotNull;

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
}
