package fr.remy.cc1.projectTradesmen.exposition;

import fr.remy.cc1.kernel.error.ValidationException;
import fr.remy.cc1.projectTradesmen.application.CreateProjectTradesmen;
import fr.remy.cc1.projectTradesmen.application.CreateProjectTradesmenCommandHandler;
import fr.remy.cc1.projectTradesmen.domain.ProjectTradesmenId;
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
@RequestMapping("/projectTradesmen")
public final class ProjectTradesmenController {
    private final CreateProjectTradesmenCommandHandler createProjectTradesmenCommandHandler;

    @Autowired
    public ProjectTradesmenController(CreateProjectTradesmenCommandHandler createProjectTradesmenCommandHandler) {
        this.createProjectTradesmenCommandHandler = createProjectTradesmenCommandHandler;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProjectTradesmenId> create(@RequestBody @Valid ProjectTradesmenRequest request) throws NoSuchEntityException, ValidationException {
        CreateProjectTradesmen createProjectTradesmen = new CreateProjectTradesmen(request.projectId, request.tradesmenId, request.tradeJob, request.dailyRate, request.currency, request.startDates, request.endDates);
        ProjectTradesmenId projectTradesmenId = createProjectTradesmenCommandHandler.handle(createProjectTradesmen);
        return ResponseEntity.status(HttpStatus.CREATED).body(projectTradesmenId);
    }
}
