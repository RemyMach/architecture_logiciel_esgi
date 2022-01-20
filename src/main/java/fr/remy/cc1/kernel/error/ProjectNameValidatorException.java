package fr.remy.cc1.kernel.error;

public class ProjectNameValidatorException extends ValidationException {
    public ProjectNameValidatorException(String errorCode, String message) {
        super(errorCode, message);
    }
}
