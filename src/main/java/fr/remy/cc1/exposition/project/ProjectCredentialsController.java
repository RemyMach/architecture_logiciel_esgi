package fr.remy.cc1.exposition.project;

import fr.remy.cc1.application.CreateProjectCredentials;
import fr.remy.cc1.application.CreateProjectCredentialsCommandHandler;
import fr.remy.cc1.domain.project.ProjectId;
import fr.remy.cc1.kernel.error.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/projectscredentials")
public final class ProjectCredentialsController {

    private final CreateProjectCredentialsCommandHandler createProjectCredentialsCommandHandler;

    @Autowired
    public ProjectCredentialsController(CreateProjectCredentialsCommandHandler createProjectCredentialsCommandHandler) {
        this.createProjectCredentialsCommandHandler = createProjectCredentialsCommandHandler;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@RequestBody @Valid ProjectCredentialsRequest request) throws ValidationException {
        CreateProjectCredentials createProjectCredentials = new CreateProjectCredentials(request.projectId, request.trade, request.skills, request.amount, request.currency, request.address, request.duration, request.durationUnit);
        ProjectId projectId = createProjectCredentialsCommandHandler.handle(createProjectCredentials);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }
}
