package by.innowise.course.mappers;

import by.innowise.course.dto.entities.PassportDto;
import by.innowise.course.entities.Passport;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PassportMapper {
    PassportMapper INSTANCE = Mappers.getMapper(PassportMapper.class);

    PassportDto passportToPassportDto(Passport passport);

    Passport passportDtoToPassport(PassportDto passportDto);
}
