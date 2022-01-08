package fr.remy.cc1.exposition.authentication;

import java.util.Objects;

public class Token {

    public final TokenId tokenId;

    public Token(TokenId tokenId) {
        this.tokenId = tokenId;
    }

    public static Token of(TokenId tokenId) {
        return new Token(tokenId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token token = (Token) o;
        return Objects.equals(tokenId, token.tokenId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tokenId);
    }
}
