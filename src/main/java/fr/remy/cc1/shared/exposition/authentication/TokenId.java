package fr.remy.cc1.shared.exposition.authentication;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TokenId tokenId = (TokenId) o;
        return Objects.equals(value, tokenId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
