package fr.remy.cc1.project.exposition;

import fr.remy.cc1.kernel.error.ValidationException;
import fr.remy.cc1.project.application.CreateProjectRequirements;
import fr.remy.cc1.project.application.CreateProjectRequirementsCommandHandler;
import fr.remy.cc1.project.domain.project.ProjectId;
import fr.remy.cc1.shared.infrastructure.exceptions.NoSuchEntityException;
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
@RequestMapping("/projectsrequirements")
public final class ProjectRequirementsController {

    private final CreateProjectRequirementsCommandHandler createProjectRequirementsCommandHandler;

    @Autowired
    public ProjectRequirementsController(CreateProjectRequirementsCommandHandler createProjectRequirementsCommandHandler) {
        this.createProjectRequirementsCommandHandler = createProjectRequirementsCommandHandler;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@RequestBody @Valid ProjectRequirementsRequest request) throws ValidationException, NoSuchEntityException {
        CreateProjectRequirements createProjectRequirements = new CreateProjectRequirements(request.projectId, request.trade, request.skills, request.amount, request.currency, request.address, request.duration, request.durationUnit);
        ProjectId projectId = createProjectRequirementsCommandHandler.handle(createProjectRequirements);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }
}
