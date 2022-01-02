package fr.remy.cc1.exposition.exception;

import fr.remy.cc1.exposition.CustomErrorResponse;
import fr.remy.cc1.exposition.CustomErrorResponseCreator;
import fr.remy.cc1.kernel.error.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class BasicExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<CustomErrorResponse> handleValidationException(ValidationException e) {
        CustomErrorResponseCreator customErrorResponseCreator = new CustomErrorResponseCreator(DomainExceptionsDictionary.codeToExpositionErrors);
        CustomErrorResponse customErrorResponse = customErrorResponseCreator.create(e.getErrorCode());
        return new ResponseEntity<>(customErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<CustomErrorResponse>> handleTypeMismatchException(MethodArgumentNotValidException e) {
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
        CustomErrorResponseCreator customErrorResponseCreator = new CustomErrorResponseCreator(DomainExceptionsDictionary.codeToExpositionErrors);
        CustomErrorResponse customErrorResponse = customErrorResponseCreator.create(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(customErrorResponse);
    }

}
