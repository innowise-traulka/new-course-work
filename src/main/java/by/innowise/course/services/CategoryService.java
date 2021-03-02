package by.innowise.course.services;

import by.innowise.course.entities.Category;

public interface CategoryService extends BaseService<Category> {
    Category save(Category category);
}
