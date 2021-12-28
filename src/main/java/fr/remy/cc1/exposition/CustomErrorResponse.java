package fr.remy.cc1.exposition;

import fr.remy.cc1.exposition.exception.ExpositionExceptionsDictionary;

public class CustomErrorResponse{
    public int errorCode;
    public String message;

    private CustomErrorResponse(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public static CustomErrorResponse from(int errorCode) {
        String message;
        if(!ExpositionExceptionsDictionary.codeToExpositionErrors.containsKey(errorCode)) {
            message = ExpositionExceptionsDictionary.codeToExpositionErrors.get(0);
        }else {
            message = ExpositionExceptionsDictionary.codeToExpositionErrors.get(errorCode);
        }
        return new CustomErrorResponse(errorCode, message);
    }
}
