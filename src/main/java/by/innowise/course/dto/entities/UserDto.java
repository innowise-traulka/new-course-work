package by.innowise.course.dto.entities;

import by.innowise.course.entities.types.UserStatus;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String email;
    private UserStatus userStatus;
    private UserRoleDto role;
}
