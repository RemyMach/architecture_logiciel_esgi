package fr.remy.cc1.exposition.exception;

import fr.remy.cc1.exposition.CustomErrorResponse;
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
        CustomErrorResponse customErrorResponse = CustomErrorResponse.from(e.getErrorCode());
        return new ResponseEntity<>(customErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<CustomErrorResponse>> handleTypeMismatchException(MethodArgumentNotValidException e) {
        List<CustomErrorResponse> errors = new ArrayList<>();
        e.getBindingResult().getAllErrors().forEach(error -> {
            System.out.println(error);
            String errorMessage = error.getDefaultMessage();
            System.out.println(errorMessage);
            if(errorMessage != null) {
                System.out.println(Integer.parseInt(errorMessage));
                CustomErrorResponse customErrorResponse = CustomErrorResponse.from(Integer.parseInt(errorMessage));
                errors.add(customErrorResponse);
            }
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<CustomErrorResponse> globalExceptionHandler(Exception e, WebRequest request) {
        System.out.println(e.getMessage());
        System.out.println(e);
        CustomErrorResponse customErrorResponse = CustomErrorResponse.from(0);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(customErrorResponse);
    }

}
