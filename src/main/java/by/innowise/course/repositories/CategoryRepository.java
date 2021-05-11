package by.innowise.course.repositories;

import by.innowise.course.entities.Category;

import java.util.List;

public interface CategoryRepository extends BaseRepository<Category> {
    List<Category> findCategoriesByHotelId(Long id);
}
