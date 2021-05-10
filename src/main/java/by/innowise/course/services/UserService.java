package by.innowise.course.services;

import by.innowise.course.dto.entities.PassportDto;
import by.innowise.course.dto.entities.UserDto;
import by.innowise.course.entities.User;

import java.util.List;

public interface UserService extends BaseService<UserDto> {
    UserDto save(UserDto userDto);

    UserDto findById(Long id);

    UserDto block(Long id);

    UserDto unblock(Long id);

    List<UserDto> findAll();
}
