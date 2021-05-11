package by.innowise.course.dto.entities;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class AuthDto {
    @Email(message = "Incorrect email")
    @NotBlank(message = "Email must be not blank")
    @Size(max = 100, message = "Email size must be less than 101 symbol")
    private String email;
    @NotBlank(message = "Password must be not blank")
    @Size(min = 8, message = "Password must be more than 8 symbols")
    private String password;
}
