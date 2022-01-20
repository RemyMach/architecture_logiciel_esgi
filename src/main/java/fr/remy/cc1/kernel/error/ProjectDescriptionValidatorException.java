package fr.remy.cc1.kernel.error;

public class ProjectDescriptionValidatorException extends ValidationException {
    public ProjectDescriptionValidatorException(String errorCode, String message) {
        super(errorCode, message);
    }
}
