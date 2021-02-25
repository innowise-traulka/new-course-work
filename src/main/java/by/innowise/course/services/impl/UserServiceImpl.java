package by.innowise.course.services.impl;

import by.innowise.course.entities.User;
import by.innowise.course.repositories.BaseRepository;
import by.innowise.course.repositories.UserRepository;
import by.innowise.course.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
