package by.innowise.course.mappers;

import by.innowise.course.dto.entities.CategoryDto;
import by.innowise.course.entities.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    @Mapping(target = "hotelId", source = "category.hotel.id")
    CategoryDto categoryToCategoryDto(Category category);

    @Mapping(target = "hotel.id", source = "categoryDto.hotelId")
    Category categoryDtoToCategory(CategoryDto categoryDto);
}
