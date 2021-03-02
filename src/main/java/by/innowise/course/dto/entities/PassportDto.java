package by.innowise.course.dto.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PassportDto {
    private Long id;
    private Integer number;
    private String serial;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate issuedAt;
    private UserDto user;
}
