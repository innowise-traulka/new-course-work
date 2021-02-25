package by.innowise.course.facade;

import by.innowise.course.dto.entities.UserDto;
import by.innowise.course.entities.User;
import by.innowise.course.entities.UserConfirmationCode;
import by.innowise.course.entities.UserRole;
import by.innowise.course.entities.types.UserRoleName;
import by.innowise.course.entities.types.UserStatus;
import by.innowise.course.exception.ApiException;
import by.innowise.course.mappers.UserMapper;
import by.innowise.course.services.EmailService;
import by.innowise.course.services.UserConfirmationCodeService;
import by.innowise.course.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.transaction.Transactional;

@Component
public class RegisterFacade {
    private final UserService userService;
    private final EmailService emailService;
    private final UserConfirmationCodeService userConfirmationCodeService;

    @Autowired
    public RegisterFacade(UserService userService,
                          EmailService emailService,
                          UserConfirmationCodeService userConfirmationCodeService) {
        this.userService = userService;
        this.emailService = emailService;
        this.userConfirmationCodeService = userConfirmationCodeService;
    }

    @Transactional
    public UserDto register(UserDto userDto) {
        try {
            User user = UserMapper.INSTANCE.userDtoToUser(userDto);
            UserConfirmationCode confirmationCode = userConfirmationCodeService.createConfirmationCode();
            confirmationCode.setUser(user);
            user.setUserConfirmationCode(confirmationCode);
            user.setUserStatus(UserStatus.NOT_CONFIRMED);
            UserRole role = new UserRole();
            role.setName(UserRoleName.USER);
            user.setRole(role);
            user = userService.save(user);
            emailService.sendMessageForConfirmAccount(user.getEmail(), confirmationCode.getCode());
            // TODO: 24/02/2021 exception
            return UserMapper.INSTANCE.userToUserDto(user);
        } catch (MessagingException e) {
            throw new ApiException("Send mail exception", e);
        }
    }
}
