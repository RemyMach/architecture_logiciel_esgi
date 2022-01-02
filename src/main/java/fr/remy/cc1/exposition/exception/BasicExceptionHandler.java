package fr.remy.cc1.exposition.exception;

import fr.remy.cc1.exposition.CustomErrorResponse;
import fr.remy.cc1.exposition.CustomErrorResponseCreator;
import fr.remy.cc1.kernel.error.BasicException;
import fr.remy.cc1.kernel.error.EmailValidatorException;
import fr.remy.cc1.kernel.error.ValidationException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class BasicExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<CustomErrorResponse> handleValidationException(ValidationException e) {
        CustomErrorResponseCreator customErrorResponseCreator = new CustomErrorResponseCreator(DomainExceptionsDictionary.codeToExpositionErrors);
        CustomErrorResponse customErrorResponse = customErrorResponseCreator.create(e.getErrorCode());
        System.out.println(e.getErrorCode());
        return new ResponseEntity<>(customErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<CustomErrorResponse>> handleTypeMismatchException(MethodArgumentNotValidException e) {
        System.out.println("on passe bien là");
        System.out.println(e);
        CustomErrorResponseCreator customErrorResponseCreator = new CustomErrorResponseCreator(ExpositionExceptionsDictionary.codeToExpositionErrors);
        List<CustomErrorResponse> errors = new ArrayList<>();
        e.getBindingResult().getAllErrors().forEach(error -> {
            String errorMessage = error.getDefaultMessage();
            if(errorMessage != null) {
                CustomErrorResponse customErrorResponse = customErrorResponseCreator.create(errorMessage);
                errors.add(customErrorResponse);
            }
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<CustomErrorResponse> globalExceptionHandler(Exception e, WebRequest request) {
        System.out.println("on passe par la default en faite");
        System.out.println(e);
        CustomErrorResponseCreator customErrorResponseCreator = new CustomErrorResponseCreator(DomainExceptionsDictionary.codeToExpositionErrors);
        CustomErrorResponse customErrorResponse = customErrorResponseCreator.create(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(customErrorResponse);
    }

}
