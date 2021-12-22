package fr.remy.cc1.exposition;

import fr.remy.cc1.exposition.exception.ExceptionsDictionary;
import org.springframework.http.HttpStatus;

public class CustomErrorResponse{
    public int errorCode;
    public String message;

    private CustomErrorResponse(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public static CustomErrorResponse from(int errorCode) {
        String message;
        if(!ExceptionsDictionary.codeToExpositionErrors.containsKey(errorCode)) {
            message = ExceptionsDictionary.codeToExpositionErrors.get(0);
        }else {
            message = ExceptionsDictionary.codeToExpositionErrors.get(errorCode);
        }
        return new CustomErrorResponse(errorCode, message);
    }
}
