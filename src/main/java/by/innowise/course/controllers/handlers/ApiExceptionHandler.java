package by.innowise.course.controllers.handlers;

import by.innowise.course.exception.ApiException;
import by.innowise.course.exception.ApiExceptionEntity;
import by.innowise.course.validator.ValidationError;
import by.innowise.course.validator.ValidationErrorBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {ApiException.class})
    public ResponseEntity<Object> handleApiRequestException(ApiException exception) {
        ApiExceptionEntity apiExceptionEntity = new ApiExceptionEntity(exception.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now());
        return ResponseEntity.badRequest().body(apiExceptionEntity);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<ValidationError> handleException(MethodArgumentNotValidException exception) {
        return ResponseEntity.badRequest().body(createValidationError(exception));
    }

    private ValidationError createValidationError(MethodArgumentNotValidException e) {
        return ValidationErrorBuilder.fromBindingErrors(e.getBindingResult());
    }
}
