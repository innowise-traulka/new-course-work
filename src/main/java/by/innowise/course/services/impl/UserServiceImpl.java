package by.innowise.course.services.impl;

import by.innowise.course.dto.entities.PassportDto;
import by.innowise.course.dto.entities.UserDto;
import by.innowise.course.entities.Passport;
import by.innowise.course.entities.User;
import by.innowise.course.exception.ApiException;
import by.innowise.course.mappers.PassportMapper;
import by.innowise.course.mappers.UserMapper;
import by.innowise.course.repositories.BaseRepository;
import by.innowise.course.repositories.UserRepository;
import by.innowise.course.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;

@Service
public class UserServiceImpl implements UserService {
    private final BaseRepository<User> userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public UserDto findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new ApiException("User not found"));
        return UserMapper.INSTANCE.userToUserDto(user);
    }

    @Override
    public PassportDto addPassportToUser(Long userId, PassportDto passportDto) {
        User user = UserMapper.INSTANCE.userDtoToUser(findById(userId));
        Passport passport = PassportMapper.INSTANCE.passportDtoToPassport(passportDto);
        passport.setUser(user);
        user.setPassport(passport);
        user = userRepository.save(user); // TODO: 02/03/2021 ???
        return PassportMapper.INSTANCE.passportToPassportDto(user.getPassport());
    }
}
