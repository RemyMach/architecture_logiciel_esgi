package fr.remy.cc1.kernel.error;

public class UserCategoryValidatorException extends ValidationException{
    public UserCategoryValidatorException(String errorCode, String message) {
        super(errorCode, message);
    }
}
