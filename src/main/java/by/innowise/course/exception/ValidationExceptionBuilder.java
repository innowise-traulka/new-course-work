package by.innowise.course.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import java.time.ZonedDateTime;
import java.util.List;

public class ValidationExceptionBuilder {
    public static ApiValidationError fromBindingErrors(Errors errors) {
        ApiValidationError exception = new ApiValidationError(HttpStatus.BAD_REQUEST,
                "Validation failed. " + errors.getErrorCount() + " error(s)", ZonedDateTime.now());
        for (ObjectError objectError : errors.getAllErrors()) {
            exception.addValidationError(objectError.getDefaultMessage());
        }
        return exception;
    }

    public static ApiValidationError fromErrorList(List<String> errorList) {
        ApiValidationError exception = new ApiValidationError(HttpStatus.BAD_REQUEST,
                errorList.size() + " error(s).", ZonedDateTime.now());
        errorList.forEach(exception::addValidationError);
        return exception;
    }
}