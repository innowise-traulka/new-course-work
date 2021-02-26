package by.innowise.course.dto.entities;

import lombok.Data;

@Data
public class AddressDto {
    private Long id;
    private String country;
    private String town;
    private String street;
    private String houseNumber;
}
