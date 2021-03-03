package by.innowise.course.dto.entities;

import by.innowise.course.entities.types.UserStatus;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserDto {
    private Long id;
    @NotBlank(message = "First name must me not blank")
    @Size(min = 2, max = 50, message = "First name size must be more than 2 and less than 50")
    private String firstName;
    @NotBlank(message = "Last name must be not blank")
    @Size(min = 2, max = 50, message = "First name size must be more than 2 and less than 50")
    private String lastName;
    @NotBlank(message = "Patronymic must be not blank")
    @Size(min = 2, max = 50, message = "First name size must be more than 2 and less than 50")
    private String patronymic;
    @Email(message = "Incorrect email")
    @NotBlank(message = "Email must be not blank")
    @Size(max = 100, message = "Email size must be less than 101 symbol")
    private String email;
    @NotBlank(message = "Password must be not blank")
    @Size(min = 8, message = "Password must be more than 8 symbols")
    private String password;
    private UserStatus userStatus;
    private UserRoleDto role;
    private PassportDto passport;
}
