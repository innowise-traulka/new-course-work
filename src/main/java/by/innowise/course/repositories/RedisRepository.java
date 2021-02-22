package by.innowise.course.repositories;

import by.innowise.course.entities.User;

import java.util.Optional;

public interface RedisRepository {
    String saveUserInRedis(User user);

    Optional<User> getUserFromRedisByToken(String token);

    void removeUserByToken(String token);
}
