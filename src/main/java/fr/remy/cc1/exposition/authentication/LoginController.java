package fr.remy.cc1.exposition.authentication;

import fr.remy.cc1.domain.user.*;
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

        User user = this.users.byEmailAndPassword(new Email(request.email), new Password(request.password));
        TokenId tokenId = this.createAuthTokenCommandHandler.handle(new CreateAuthToken(user.getUserId(), user.getEmail()));
        return ResponseEntity.ok(tokenId);
    }

}
