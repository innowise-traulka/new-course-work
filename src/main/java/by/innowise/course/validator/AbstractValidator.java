package by.innowise.course.validator;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public abstract class AbstractValidator<T> implements Validator<T> {
    protected final List<String> errorList = new ArrayList<>();
    
    protected void addError(String error) {
        errorList.add(error);
    }
}
