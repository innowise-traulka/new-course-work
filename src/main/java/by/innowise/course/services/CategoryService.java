package by.innowise.course.services;

import by.innowise.course.dto.entities.CategoryDto;
import by.innowise.course.entities.Category;

public interface CategoryService extends BaseService<Category> {
    CategoryDto save(CategoryDto categoryDto);
}
