package fr.remy.cc1.infrastructure.authentication.token;

import fr.remy.cc1.domain.UserId;
import fr.remy.cc1.exposition.authentication.Token;
import fr.remy.cc1.exposition.authentication.TokenId;
import fr.remy.cc1.exposition.authentication.Tokens;
import fr.remy.cc1.infrastructure.exceptions.InfrastructureExceptionsDictionary;
import fr.remy.cc1.infrastructure.exceptions.NoSuchEntityException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryToken implements Tokens {

    @Value("${JWT_SECRET_KEY}")
    private String SECRET_KEY;

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
    public TokenId nextIdentity(UserId userId) throws UnsupportedEncodingException {
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(this.SECRET_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS256.getJcaName());
        String jws = Jwts.builder()
                .setSubject("Bob")
                .claim("userId", userId.getValue())
                .signWith(signingKey)
                .compact();

        return TokenId.of(jws);
    }
}
