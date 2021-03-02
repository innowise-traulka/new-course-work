package by.innowise.course.mappers;

import by.innowise.course.dto.entities.CategoryDto;
import by.innowise.course.entities.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDto categoryToCategoryDto(Category category);

    Category categoryDtoToCategory(CategoryDto categoryDto);
}
