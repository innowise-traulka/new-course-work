package by.innowise.course.services;

import by.innowise.course.dto.entities.PassportDto;
import org.springframework.transaction.annotation.Transactional;

public interface PassportService extends BaseService<PassportDto> {
    @Transactional
    PassportDto addPassportToUser(Long userId, PassportDto passportDto);
}
