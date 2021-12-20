package fr.remy.cc1.exposition;

import fr.remy.cc1.kernel.error.BasicException;
import fr.remy.cc1.kernel.error.EmailValidatorException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class BasicExceptionHandler {

    @ResponseBody
    @ExceptionHandler(EmailValidatorException.class)
    public ResponseError handleTypeMismatchException(EmailValidatorException e) {
        System.out.println("on passe dans le handler");
        return new ResponseError(HttpStatus.BAD_REQUEST, e.getErrorCode(), e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public String globalExceptionHandler(Exception e, WebRequest request) {
        System.out.println("on passe par là");
        return e.getMessage();
        //return new ResponseError(HttpStatus.BAD_REQUEST, 10, e.getMessage());
    }

}
