package by.innowise.course.dto.entities;

import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class AddressDto {
    private Long id;
    @NotBlank(message = "Country must be not blank")
    @Size(min = 2, max = 50, message = "Country must be more than 2 and less that 51 symbols")
    private String country;
    @NotBlank(message = "Town must be not blank")
    @Size(min = 2, max = 50, message = "Town must be more than 2 and less that 51 symbols")
    private String town;
    @NotBlank(message = "Street must be not blank")
    @Size(min = 2, max = 50, message = "Street must be more than 2 and less that 51 symbols")
    private String street;
    @Digits(integer = 3, fraction = 0, message = "House number must be less than 4 symbols")
    private String houseNumber;
}
