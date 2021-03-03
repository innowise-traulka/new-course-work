package by.innowise.course.dto.entities;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
public class RoomDto {
    private Long id;
    @Min(value = 0, message = "Number must be more than 0")
    @Max(value = 10000, message = "Number must be less than 10001")
    private Integer number;
    private Long categoryId;
}
