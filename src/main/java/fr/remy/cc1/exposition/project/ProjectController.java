package fr.remy.cc1.exposition.project;

import fr.remy.cc1.application.CreateProject;
import fr.remy.cc1.application.CreateProjectCommandHandler;
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
@RequestMapping("/projects")
public final class ProjectController {

    private final CreateProjectCommandHandler createProjectCommandHandler;

    @Autowired
    public ProjectController(CreateProjectCommandHandler createProjectCommandHandler) {
        this.createProjectCommandHandler = createProjectCommandHandler;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProjectId> create(@RequestBody @Valid ProjectRequest request) throws ValidationException {
        CreateProject createProject = new CreateProject(request.name, request.description);
        ProjectId projectId = createProjectCommandHandler.handle(createProject);
        return ResponseEntity.status(HttpStatus.CREATED).body(projectId);
    }
}
