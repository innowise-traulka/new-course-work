package by.innowise.course.dto.entities;

import by.innowise.course.entities.types.CategoryName;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CategoryDto {
    private Long id;
    private CategoryName categoryName;
    private BigDecimal price;
    private HotelDto hotel;
}
