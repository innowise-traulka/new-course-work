package by.innowise.course.services;

import by.innowise.course.dto.entities.PassportDto;
import by.innowise.course.dto.entities.UserDto;
import by.innowise.course.entities.User;

public interface UserService extends BaseService<UserDto> {
    User save(User user);

    UserDto findById(Long id);
}
