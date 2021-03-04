package by.innowise.course.exception;

import java.util.Collections;
import java.util.List;

public class ApiRequestException extends ApiException {
    private final List<String> errorList;

    public ApiRequestException(String message, List<String> errorList) {
        super(message);
        this.errorList = errorList;
    }

    public List<String> getErrorList() {
        return Collections.unmodifiableList(errorList);
    }
}
