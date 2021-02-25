package by.innowise.course.repositories;

import by.innowise.course.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User> {
    @Query("Select user from User user inner join user.userConfirmationCode confirmCode" +
            " on user.id=confirmCode.id where confirmCode.code = :code")
    Optional<User> findByConfirmationCode(@Param("code") String code);
}
