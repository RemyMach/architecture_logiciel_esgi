package fr.remy.cc1.exposition.exception;

import fr.remy.cc1.exposition.CustomErrorResponse;
import fr.remy.cc1.exposition.CustomErrorResponseCreator;
import fr.remy.cc1.infrastructure.exceptions.NoSuchEntityException;
import fr.remy.cc1.kernel.error.BasicException;
import fr.remy.cc1.kernel.error.PaymentProcessValidationException;
import fr.remy.cc1.kernel.error.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class BasicExceptionHandler {

    @ExceptionHandler({ValidationException.class, PaymentProcessValidationException.class})
    public ResponseEntity<CustomErrorResponse> handleValidationException(BasicException e) {
        System.out.println("je passe bien là après l'erreur PaymentProcessValidationException");
        CustomErrorResponseCreator customErrorResponseCreator = new CustomErrorResponseCreator(DomainExceptionsDictionary.codeToExpositionErrors);
        CustomErrorResponse customErrorResponse = customErrorResponseCreator.create(e.getErrorCode());
        return new ResponseEntity<>(customErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchEntityException.class)
    public ResponseEntity<CustomErrorResponse> handleNoSuchEntityException(NoSuchEntityException e) {
        CustomErrorResponseCreator customErrorResponseCreator = new CustomErrorResponseCreator(InfrastructureExceptionsDictionary.codeToExpositionErrors);
        CustomErrorResponse customErrorResponse = customErrorResponseCreator.create(e.getErrorCode());
        return new ResponseEntity<>(customErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleTypeMismatchException(MethodArgumentNotValidException e) {
        CustomErrorResponseCreator customErrorResponseCreator = new CustomErrorResponseCreator(ExpositionExceptionsDictionary.codeToExpositionErrors);
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(error -> {
            String errorMessage = error.getDefaultMessage();
            if(errorMessage != null) {
                CustomErrorResponse customErrorResponse = customErrorResponseCreator.create(errorMessage);
                errors.put("errorCode", customErrorResponse.errorCode);
                errors.put("message", customErrorResponse.message);
            }
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<CustomErrorResponse> globalExceptionHandler(Exception e, WebRequest request) {
        System.out.println(e);
        CustomErrorResponseCreator customErrorResponseCreator = new CustomErrorResponseCreator(DomainExceptionsDictionary.codeToExpositionErrors);
        CustomErrorResponse customErrorResponse = customErrorResponseCreator.create(e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(customErrorResponse);
    }

}
