package by.innowise.course.services;

import by.innowise.course.dto.entities.UserConfirmationCodeDto;
import by.innowise.course.entities.UserConfirmationCode;

public interface UserConfirmationCodeService extends BaseService<UserConfirmationCodeDto> {
    UserConfirmationCode createConfirmationCode();

    UserConfirmationCodeDto save(UserConfirmationCodeDto userConfirmationCodeDto);
}
