package fr.remy.cc1.member.exposition;

import fr.remy.cc1.kernel.error.ValidationException;
import fr.remy.cc1.member.application.CreateContractor;
import fr.remy.cc1.member.application.CreateContractorCommandHandler;
import fr.remy.cc1.member.application.CreateTradesman;
import fr.remy.cc1.member.application.CreateTradesmanCommandHandler;
import fr.remy.cc1.shared.domain.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@SuppressWarnings("unused")
@RestController
@RequestMapping("/users")
public class UserController {

    private final CreateTradesmanCommandHandler createTradesmanCommandHandler;
    private final CreateContractorCommandHandler createContractorCommandHandler;

    @Autowired
    public UserController(CreateTradesmanCommandHandler createTradesmanCommandHandler, CreateContractorCommandHandler createContractorCommandHandler) {
        this.createTradesmanCommandHandler = createTradesmanCommandHandler;
        this.createContractorCommandHandler = createContractorCommandHandler;
    }

    @PostMapping(path = "/contractor", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@RequestBody @Valid ContractorRequest request) throws ValidationException {
        CreateContractor createContractor = new CreateContractor(request.lastname, request.firstname, request.email, request.password, request.companySiren, request.companyName);
        UserId userId = createContractorCommandHandler.handle(createContractor);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @PostMapping(path = "/tradesman", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@RequestBody @Valid TradesmanRequest request) throws ValidationException {
        CreateTradesman createTradesman = new CreateTradesman(request.lastname, request.firstname, request.email, request.password);
        UserId userId = createTradesmanCommandHandler.handle(createTradesman);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }
}
