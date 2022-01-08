package fr.remy.cc1.exposition.authentication;

public class TokenId {

    private final String value;

    private TokenId(String value) {
        this.value = value;
    }

    public static TokenId of(String value) {
        return new TokenId(value);
    }

    public String getValue() {
        return String.valueOf(value);
    }

}
