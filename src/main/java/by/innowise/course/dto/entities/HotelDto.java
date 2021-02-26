package by.innowise.course.dto.entities;

import lombok.Data;

@Data
public class HotelDto {
    private Long id;
    private String name;
    private AddressDto address;
}
