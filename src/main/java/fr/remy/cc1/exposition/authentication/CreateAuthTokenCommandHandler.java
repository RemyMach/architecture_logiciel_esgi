package fr.remy.cc1.exposition.authentication;

import fr.remy.cc1.kernel.CommandHandler;
import fr.remy.cc1.kernel.error.ValidationException;

public class CreateAuthTokenCommandHandler implements CommandHandler<CreateAuthToken, TokenId > {

    private final Tokens tokens;

    public CreateAuthTokenCommandHandler(Tokens tokens) {
        this.tokens = tokens;
    }

    @Override
    public TokenId handle(CreateAuthToken command)  {

        TokenId tokenId = this.tokens.nextIdentity();
        this.tokens.save(Token.of(tokenId), command.userId);
        return tokenId;
    }
}
