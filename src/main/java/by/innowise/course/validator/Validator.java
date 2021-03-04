package by.innowise.course.validator;

import by.innowise.course.exception.ApiRequestException;
import by.innowise.course.validator.type.ValidatorType;

import java.util.List;

public interface Validator<T> {
    void validate(T t);

    ValidatorType name();

    default void throwException(String message, List<String> errorList) {
        if (errorList.size() > 0) {
            throw new ApiRequestException(message, errorList);
        }
    }
}
