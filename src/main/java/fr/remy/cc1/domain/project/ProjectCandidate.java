package fr.remy.cc1.domain.project;

import fr.remy.cc1.kernel.error.ValidationException;

public class ProjectCandidate {

    public final String name;
    public final String description;

    private ProjectCandidate(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public static ProjectCandidate of(String name, String description) throws ValidationException {
        ProjectCandidate projectCandidate = new ProjectCandidate(name, description);
        if (ValidationProjectCandidateEngine.getInstance().test(projectCandidate)) {
            return projectCandidate;
        }
        return null;
    }
}
