package fr.remy.cc1.project.domain.project;

import fr.remy.cc1.kernel.error.ExceptionsDictionary;
import fr.remy.cc1.kernel.error.ProjectDescriptionValidatorException;
import fr.remy.cc1.kernel.error.ProjectNameValidatorException;
import fr.remy.cc1.kernel.error.ValidationException;

public final class ValidationProjectCandidateEngine {

    private static final ValidationProjectCandidateEngine INSTANCE = new ValidationProjectCandidateEngine();

    private ValidationProjectCandidateEngine() {
    }

    public static ValidationProjectCandidateEngine getInstance() {
        return INSTANCE;
    }

    public boolean test(ProjectCandidate projectCandidate) throws ValidationException{
        return this.validateName(projectCandidate.name) && this.validateDescription(projectCandidate.description);
    }

    private boolean validateName(String name) throws ProjectNameValidatorException {
        if (name != null && !name.isEmpty()) {
            return true;
        }
        throw new ProjectNameValidatorException(ExceptionsDictionary.INVALID_PROJECT_NAME.getErrorCode(), ExceptionsDictionary.INVALID_PROJECT_NAME.getMessage());
    }

    private boolean validateDescription(String description) throws ProjectDescriptionValidatorException {
        if (description != null && !description.isEmpty()) {
            return true;
        }
        throw new ProjectDescriptionValidatorException(ExceptionsDictionary.INVALID_PROJECT_DESCRIPTION.getErrorCode(), ExceptionsDictionary.INVALID_PROJECT_DESCRIPTION.getMessage());
    }
}
