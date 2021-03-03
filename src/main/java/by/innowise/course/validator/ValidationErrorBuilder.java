package by.innowise.course.validator;

import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import java.time.ZonedDateTime;

public class ValidationErrorBuilder {

    public static ValidationError fromBindingErrors(Errors errors) {
        ValidationError error = new ValidationError(HttpStatus.BAD_REQUEST, "Validation failed. "
                + errors.getErrorCount() + " error(s)", ZonedDateTime.now());
        for (ObjectError objectError : errors.getAllErrors()) {
            error.addValidationError(objectError.getDefaultMessage());
        }
        return error;
    }
}