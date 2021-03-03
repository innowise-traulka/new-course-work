package by.innowise.course.dto.entities;

import by.innowise.course.entities.types.CategoryName;
import lombok.Data;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

@Data
public class CategoryDto {
    private Long id;
    private CategoryName categoryName;
    @DecimalMin(value = "0.0", message = "Price must be more than 0")
    @DecimalMax(value = "1000000.0", message = "price must be less than 1000000")
    private BigDecimal price;
    private Long hotelId;
}
