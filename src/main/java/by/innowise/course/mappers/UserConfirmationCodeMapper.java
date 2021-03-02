package by.innowise.course.mappers;

import by.innowise.course.dto.entities.UserConfirmationCodeDto;
import by.innowise.course.entities.Room;
import by.innowise.course.entities.UserConfirmationCode;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserConfirmationCodeMapper {
    UserConfirmationCodeMapper INSTANCE = Mappers.getMapper(UserConfirmationCodeMapper.class);

    UserConfirmationCodeDto codeToCodeDto(UserConfirmationCode userConfirmationCode);

    UserConfirmationCode codeDtoToCode(UserConfirmationCodeDto userConfirmationCodeDto);
}
