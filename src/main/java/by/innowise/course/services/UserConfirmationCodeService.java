package by.innowise.course.services;

import by.innowise.course.entities.UserConfirmationCode;

public interface UserConfirmationCodeService extends BaseService<UserConfirmationCode> {
    UserConfirmationCode createConfirmationCode();
}
