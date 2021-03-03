package by.innowise.course.dto.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class PassportDto {
    private Long id;
    @Digits(integer = 7, fraction = 0, message = "House number must be less than 8 symbols")
    private Integer number;
    @NotBlank(message = "Serial must be not blank")
    @Size(max = 2, message = "Serial must be less than 3 symbols")
    private String serial;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate issuedAt;
    private UserDto user;
}
