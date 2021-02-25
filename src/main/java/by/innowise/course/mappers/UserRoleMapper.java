package by.innowise.course.mappers;

import by.innowise.course.dto.entities.UserRoleDto;
import by.innowise.course.entities.UserRole;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserRoleMapper {
    UserRoleMapper INSTANCE = Mappers.getMapper(UserRoleMapper.class);

    UserRoleDto userRoleToUserRoleDto(UserRole userRole);

    UserRole userRoleDtoToUserRole(UserRoleDto userRoleDto);
}
