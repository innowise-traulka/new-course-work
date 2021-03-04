package by.innowise.course.services.impl;

import by.innowise.course.dto.entities.PassportDto;
import by.innowise.course.dto.entities.UserDto;
import by.innowise.course.entities.Passport;
import by.innowise.course.entities.User;
import by.innowise.course.exception.ApiException;
import by.innowise.course.mappers.PassportMapper;
import by.innowise.course.mappers.UserConfirmationCodeMapper;
import by.innowise.course.mappers.UserMapper;
import by.innowise.course.repositories.BaseRepository;
import by.innowise.course.repositories.PassportRepository;
import by.innowise.course.services.PassportService;
import by.innowise.course.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PassportServiceImpl implements PassportService {
    private final PassportRepository passportRepository;
    private final UserService userService;

    @Autowired
    public PassportServiceImpl(PassportRepository passportRepository, UserService userService) {
        this.passportRepository = passportRepository;
        this.userService = userService;
    }

    @Transactional
    @Override
    public PassportDto addPassportToUser(Long userId, PassportDto passportDto) {
        User user = UserMapper.INSTANCE.userDtoToUser(userService.findById(userId));
        Passport passport = PassportMapper.INSTANCE.passportDtoToPassport(passportDto);
        passport.setUser(user);
        user.setPassport(passport);
         // TODO: 02/03/2021 exception not already init passport
        userService.save(UserMapper.INSTANCE.userToUserDto(user));
        return findByUserId(userId);
    }

    @Override
    public PassportDto save(PassportDto passportDto) {
        Passport passport = PassportMapper.INSTANCE.passportDtoToPassport(passportDto);
        return PassportMapper.INSTANCE.passportToPassportDto(passportRepository.save(passport));
    }

    @Override
    public PassportDto findById(Long id) {
        Passport passport = passportRepository.findById(id).orElseThrow(() ->
                new ApiException("Passport not found"));
        return PassportMapper.INSTANCE.passportToPassportDto(passport);
    }

    @Override
    public PassportDto findByUserId(Long id) {
        Passport passport = passportRepository.findByUserId(id).orElseThrow(() ->
                new ApiException("Passport by user id not found"));
        return PassportMapper.INSTANCE.passportToPassportDto(passport);
    }
}
