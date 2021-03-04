package by.innowise.course.config;

import by.innowise.course.validator.Validator;
import by.innowise.course.validator.type.ValidatorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class ValidationDispatcher implements Dispatcher<ValidatorType, Validator> {

    private final Map<ValidatorType, Validator> map;

    @Autowired
    public ValidationDispatcher(List<Validator> validators) {
        this.map = validators.stream().collect(Collectors.toMap(Validator::name, v -> v));
    }

    @Override
    public Validator getByName(ValidatorType name) {
        return map.get(name);
    }
}
