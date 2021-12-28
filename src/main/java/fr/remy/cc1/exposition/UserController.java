package fr.remy.cc1.exposition;

import fr.remy.cc1.application.CreateUser;
import fr.remy.cc1.application.CreateUserCommandHandler;
import fr.remy.cc1.domain.user.UserCategory;
import fr.remy.cc1.domain.user.UserCategoryCreator;
import fr.remy.cc1.kernel.error.BasicException;
import fr.remy.cc1.kernel.error.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@SuppressWarnings("unused")
@RestController
@RequestMapping("/users")
public class UserController {

    private final CreateUserCommandHandler createUserCommandHandler;

    @Autowired
    public UserController(CreateUserCommandHandler createUserCommandHandler) {
        this.createUserCommandHandler = createUserCommandHandler;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@RequestBody @Valid UserRequest request) throws ValidationException {
        CreateUser createUser = new CreateUser(request.lastname, request.firstname, request.email, request.password, UserCategoryCreator.getInstance().getValueOf(request.userCategory));
        createUserCommandHandler.handle(createUser);
        return ResponseEntity.ok(null);
    }
}
