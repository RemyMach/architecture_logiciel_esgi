package fr.remy.cc1.projectTradesmen.exposition;

import fr.remy.cc1.infrastructure.exceptions.NoSuchEntityException;
import fr.remy.cc1.projectTradesmen.application.CreateProjectTradesmen;
import fr.remy.cc1.projectTradesmen.application.CreateProjectTradesmenCommandHandler;
import fr.remy.cc1.projectTradesmen.domain.ProjectTradesmenId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.ValidationException;

@RestController
@RequestMapping("/projectTradesmen")
public final class ProjectTradesmenController {
    private final CreateProjectTradesmenCommandHandler createProjectTradesmenCommandHandler;

    @Autowired
    public ProjectTradesmenController(CreateProjectTradesmenCommandHandler createProjectTradesmenCommandHandler) {
        this.createProjectTradesmenCommandHandler = createProjectTradesmenCommandHandler;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProjectTradesmenId> create(@RequestBody @Valid ProjectTradesmenRequest request) throws ValidationException, NoSuchEntityException {
        CreateProjectTradesmen createProjectTradesmen = new CreateProjectTradesmen(request.projectId, request.tradesmenId);
        ProjectTradesmenId projectTradesmenId = createProjectTradesmenCommandHandler.handle(createProjectTradesmen);
        return ResponseEntity.status(HttpStatus.CREATED).body(projectTradesmenId);
    }
}
