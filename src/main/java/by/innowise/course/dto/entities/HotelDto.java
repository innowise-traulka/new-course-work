package by.innowise.course.dto.entities;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class HotelDto {
    private Long id;
    @NotBlank(message = "Name must be not blank")
    @Size(min = 2, max = 100, message = "Name must be more than 2 and less than 101 symbol")
    private String name;
    private AddressDto address;
}
