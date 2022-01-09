package fr.remy.cc1.exposition.exception;

import fr.remy.cc1.exposition.CustomErrorResponse;
import fr.remy.cc1.exposition.CustomErrorResponseCreator;
import fr.remy.cc1.exposition.exception.authentication.AuthFailedException;
import fr.remy.cc1.exposition.exception.authentication.AuthRequiredException;
import fr.remy.cc1.infrastructure.exceptions.NoSuchEntityException;
import fr.remy.cc1.kernel.error.BasicException;
import fr.remy.cc1.kernel.error.PaymentProcessValidationException;
import fr.remy.cc1.kernel.error.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class BasicExceptionHandler {

    @ExceptionHandler(AuthRequiredException.class)
    public ResponseEntity<CustomErrorResponse> authRequiredExceptionHandler(AuthRequiredException e) {
        CustomErrorResponseCreator customErrorResponseCreator = new CustomErrorResponseCreator(ExpositionExceptionsDictionaryMapper.codeToExpositionErrors);
        CustomErrorResponse customErrorResponse = customErrorResponseCreator.create(e.getErrorCode());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(customErrorResponse);
    }

    @ExceptionHandler(AuthFailedException.class)
    public ResponseEntity<CustomErrorResponse> authFailedExceptionHandler(AuthFailedException e) {
        CustomErrorResponseCreator customErrorResponseCreator = new CustomErrorResponseCreator(ExpositionExceptionsDictionaryMapper.codeToExpositionErrors);
        CustomErrorResponse customErrorResponse = customErrorResponseCreator.create(e.getErrorCode());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(customErrorResponse);
    }

    @ExceptionHandler({ValidationException.class, PaymentProcessValidationException.class})
    public ResponseEntity<CustomErrorResponse> handleValidationException(BasicException e) {
        CustomErrorResponseCreator customErrorResponseCreator = new CustomErrorResponseCreator(DomainExceptionsDictionaryMapper.codeToExpositionErrors);
        CustomErrorResponse customErrorResponse = customErrorResponseCreator.create(e.getErrorCode());
        return new ResponseEntity<>(customErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchEntityException.class)
    public ResponseEntity<CustomErrorResponse> handleNoSuchEntityException(NoSuchEntityException e) {
        CustomErrorResponseCreator customErrorResponseCreator = new CustomErrorResponseCreator(InfrastructureExceptionsDictionaryMapper.codeToExpositionErrors);
        CustomErrorResponse customErrorResponse = customErrorResponseCreator.create(e.getErrorCode());
        return new ResponseEntity<>(customErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleTypeMismatchException(MethodArgumentNotValidException e) {
        CustomErrorResponseCreator customErrorResponseCreator = new CustomErrorResponseCreator(ExpositionExceptionsDictionaryMapper.codeToExpositionErrors);
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
    public ResponseEntity<CustomErrorResponse> globalExceptionHandler(Exception e) {
        System.out.println(e);
        CustomErrorResponseCreator customErrorResponseCreator = new CustomErrorResponseCreator(DomainExceptionsDictionaryMapper.codeToExpositionErrors);
        CustomErrorResponse customErrorResponse = customErrorResponseCreator.create("");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(customErrorResponse);
    }

}
