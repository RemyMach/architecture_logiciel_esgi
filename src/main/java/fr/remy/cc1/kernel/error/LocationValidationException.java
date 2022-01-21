package fr.remy.cc1.kernel.error;

public class LocationValidationException extends ValidationException{
    public LocationValidationException(String errorCode, String message) {
        super(errorCode, message);
    }
}
