package by.innowise.course.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ApiValidationError extends ApiEntityException {
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<String> errors = new ArrayList<>();

    public ApiValidationError(HttpStatus httpStatus, String errorMessage, ZonedDateTime timestamp) {
        super(errorMessage, httpStatus, timestamp);
    }

    public void addValidationError(String error) {
        errors.add(error);
    }
}
