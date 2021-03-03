package by.innowise.course.validator;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class ValidationError {
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<String> errors = new ArrayList<>();
    private HttpStatus httpStatus;
    private final String errorMessage;
    private final ZonedDateTime timestamp;

    public ValidationError(HttpStatus httpStatus, String errorMessage, ZonedDateTime timestamp) {
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
        this.timestamp = timestamp;
    }

    public void addValidationError(String error) {
        errors.add(error);
    }
}
