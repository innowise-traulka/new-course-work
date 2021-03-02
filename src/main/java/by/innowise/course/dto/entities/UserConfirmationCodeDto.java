package by.innowise.course.dto.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserConfirmationCodeDto {
    private Long id;
    private String code;
    private Long userId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdAt;
}
