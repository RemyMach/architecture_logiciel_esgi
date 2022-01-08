package fr.remy.cc1.infrastructure.authentication.token;

import fr.remy.cc1.domain.user.User;
import fr.remy.cc1.domain.user.UserId;
import fr.remy.cc1.exposition.authentication.Token;
import fr.remy.cc1.exposition.authentication.TokenId;
import fr.remy.cc1.exposition.authentication.Tokens;
import fr.remy.cc1.infrastructure.exceptions.InfrastructureExceptionsDictionary;
import fr.remy.cc1.infrastructure.exceptions.NoSuchEntityException;

import java.security.SecureRandom;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryToken implements Tokens {

    private final Map<UserId, Token> tokensData = new ConcurrentHashMap<>();
    @Override
    public void save(Token token, UserId userId) {
        tokensData.put(userId, token);
    }

    @Override
    public Token byUserId(UserId userId) throws NoSuchEntityException {
        final Token token = tokensData.get(userId);
        if (token == null) {
            throw new NoSuchEntityException(InfrastructureExceptionsDictionary.TOKEN_NOT_FOUND.getErrorCode(), InfrastructureExceptionsDictionary.TOKEN_NOT_FOUND.getMessage());
        }
        return token;
    }

    @Override
    public TokenId nextIdentity() {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[32];
        random.nextBytes(bytes);
        String token = bytes.toString();
        return TokenId.of(token);
    }
}
