package fr.remy.cc1.exposition.authentication;

public class Token {

    private final TokenId tokenId;

    public Token(TokenId tokenId) {
        this.tokenId = tokenId;
    }

    public static Token of(TokenId tokenId) {
        return new Token(tokenId);
    }
}
