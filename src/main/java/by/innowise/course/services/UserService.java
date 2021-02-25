package by.innowise.course.services;

import by.innowise.course.entities.User;

public interface UserService extends BaseService<User> {
    User save(User user);
}
