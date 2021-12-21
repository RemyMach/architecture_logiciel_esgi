package fr.remy.cc1.exposition.exception;

import fr.remy.cc1.exposition.CustomErrorResponse;
import fr.remy.cc1.kernel.error.BasicException;
import fr.remy.cc1.kernel.error.EmailValidatorException;
import fr.remy.cc1.kernel.error.ExceptionsDictionary;
import fr.remy.cc1.kernel.error.ValidationException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class BasicExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<CustomErrorResponse> handleValidationException(ValidationException e) {
        CustomErrorResponse customErrorResponse = new CustomErrorResponse(e.getErrorCode(), e.getMessage());
        return new ResponseEntity<>(customErrorResponse, HttpStatus.BAD_REQUEST);
    }

    //TODO mappage d'un code d'excpetion dans le message du validator vers un objet exeption dans la partie exposition
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomErrorResponse> handleTypeMismatchException(MethodArgumentNotValidException e) {
        CustomErrorResponse customErrorResponse = new CustomErrorResponse(10, e.getMessage());
        return new ResponseEntity<>(customErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<CustomErrorResponse> globalExceptionHandler(Exception e, WebRequest request) {
        System.out.println("on passe par là");
        CustomErrorResponse customErrorResponse = new CustomErrorResponse(ExceptionsDictionary.PAYMENT_NOT_PRESENT.getErrorCode(), ExceptionsDictionary.PAYMENT_NOT_PRESENT.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(customErrorResponse);
    }

}
