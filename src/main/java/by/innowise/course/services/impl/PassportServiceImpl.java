package by.innowise.course.services.impl;

import by.innowise.course.dto.entities.PassportDto;
import by.innowise.course.entities.Passport;
import by.innowise.course.entities.User;
import by.innowise.course.mappers.PassportMapper;
import by.innowise.course.mappers.UserMapper;
import by.innowise.course.services.PassportService;
import by.innowise.course.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PassportServiceImpl implements PassportService {
    private final UserService userService;

    @Autowired
    public PassportServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Transactional
    @Override
    public PassportDto addPassportToUser(Long userId, PassportDto passportDto) {
        User user = UserMapper.INSTANCE.userDtoToUser(userService.findById(userId));
        Passport passport = PassportMapper.INSTANCE.passportDtoToPassport(passportDto);
        passport.setUser(user);
        user.setPassport(passport);
        user = userService.save(user); // TODO: 02/03/2021 exception not already init passport
        return PassportMapper.INSTANCE.passportToPassportDto(user.getPassport());
    }
}
