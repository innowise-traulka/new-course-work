package by.innowise.course.services;

import by.innowise.course.dto.entities.UserDto;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends BaseService<UserDto>, UserDetailsService {
    UserDto save(UserDto userDto);

    UserDto findById(Long id);

    UserDto block(Long id);

    UserDto unblock(Long id);

    List<UserDto> findAll();

    UserDto findByToken(String token);

    UserDto makeAdmin(Long id);
}
