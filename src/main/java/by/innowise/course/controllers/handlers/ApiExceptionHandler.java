package by.innowise.course.controllers.handlers;

import by.innowise.course.exception.ApiException;
import by.innowise.course.exception.ApiEntityException;
import by.innowise.course.exception.ApiRequestException;
import by.innowise.course.exception.ApiValidationError;
import by.innowise.course.exception.ValidationExceptionBuilder;
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
        return ResponseEntity.badRequest().body(createApiError(exception));
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<ApiValidationError> handleException(MethodArgumentNotValidException exception) {
        return ResponseEntity.badRequest().body(createValidationError(exception));
    }

    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<ApiValidationError> handleException(ApiRequestException exception) {
        return ResponseEntity.badRequest().body(createValidationError(exception));
    }

    private ApiEntityException createApiError(ApiException e) {
        return new ApiEntityException(e.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now());
    }

    private ApiValidationError createValidationError(MethodArgumentNotValidException e) {
        return ValidationExceptionBuilder.fromBindingErrors(e.getBindingResult());
    }

    private ApiValidationError createValidationError(ApiRequestException e) {
        return ValidationExceptionBuilder.fromErrorList(e.getErrorList());
    }
}
