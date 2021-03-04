package by.innowise.course.repositories;

import by.innowise.course.entities.Passport;

import java.util.Optional;

public interface PassportRepository extends BaseRepository<Passport> {
    Optional<Passport> findByUserId(Long id);
}
