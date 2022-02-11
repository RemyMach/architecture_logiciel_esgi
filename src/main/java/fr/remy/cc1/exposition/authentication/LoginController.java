package fr.remy.cc1.exposition.authentication;

import fr.remy.cc1.member.domain.user.Email;
import fr.remy.cc1.member.domain.user.Password;
import fr.remy.cc1.member.domain.user.User;
import fr.remy.cc1.member.domain.user.Users;
import fr.remy.cc1.exposition.exception.ExpositionExceptionsDictionary;
import fr.remy.cc1.exposition.exception.authentication.AuthPasswordEmailNotMatchException;
import fr.remy.cc1.infrastructure.exceptions.NoSuchEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/login")
public class LoginController {
    private final CreateAuthTokenCommandHandler createAuthTokenCommandHandler;

    private final Users users;

    @Autowired
    public LoginController(CreateAuthTokenCommandHandler createAuthTokenCommandHandler, Users users) {
        this.createAuthTokenCommandHandler = createAuthTokenCommandHandler;
        this.users = users;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TokenId> create(@RequestBody @Valid LoginRequest request) throws Exception {
        try {
            User user = this.users.byEmailAndPassword(new Email(request.email), new Password(request.password));
            TokenId tokenId = this.createAuthTokenCommandHandler.handle(new CreateAuthToken(user.getUserId(), user.getEmail()));
            return ResponseEntity.ok(tokenId);
        }catch (NoSuchEntityException noSuchEntityException) {
            throw new AuthPasswordEmailNotMatchException(
                    ExpositionExceptionsDictionary.PASSWORD_EMAIL_AUTH_FAILED.getErrorCode(),
                    ExpositionExceptionsDictionary.PASSWORD_EMAIL_AUTH_FAILED.getMessage()
            );
        }
    }

}
