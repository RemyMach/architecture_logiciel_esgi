package fr.remy.cc1.kernel.error;

public class CertificateValidationException extends ValidationException{
    public CertificateValidationException(String errorCode, String message) {
        super(errorCode, message);
    }
}
