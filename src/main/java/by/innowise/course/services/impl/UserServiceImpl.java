package by.innowise.course.services.impl;

import by.innowise.course.dto.entities.UserDto;
import by.innowise.course.entities.User;
import by.innowise.course.entities.types.UserStatus;
import by.innowise.course.exception.ApiException;
import by.innowise.course.mappers.UserMapper;
import by.innowise.course.repositories.BaseRepository;
import by.innowise.course.repositories.UserRepository;
import by.innowise.course.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final BaseRepository<User> userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto save(UserDto userDto) {
        User user = UserMapper.INSTANCE.userDtoToUser(userDto);
        return UserMapper.INSTANCE.userToUserDto(userRepository.save(user));
    }

    @Override
    public UserDto findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new ApiException("User not found"));
        return UserMapper.INSTANCE.userToUserDto(user);
    }

    @Transactional
    @Override
    public UserDto block(Long id) { // TODO: 03/03/2021 validator is blocked?
        User user = UserMapper.INSTANCE.userDtoToUser(findById(id));
        user.setUserStatus(UserStatus.BLOCKED);
        return UserMapper.INSTANCE.userToUserDto(userRepository.save(user));
    }

    @Transactional
    @Override
    public UserDto unblock(Long id) {
        User user = UserMapper.INSTANCE.userDtoToUser(findById(id));
        user.setUserStatus(UserStatus.ACTIVE);
        return UserMapper.INSTANCE.userToUserDto(userRepository.save(user));
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(UserMapper.INSTANCE::userToUserDto)
                .collect(Collectors.toList());
    }
}
