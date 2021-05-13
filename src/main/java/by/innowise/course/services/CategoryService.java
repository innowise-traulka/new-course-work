package by.innowise.course.services;

import by.innowise.course.dto.entities.CategoryDto;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

public interface CategoryService extends BaseService<CategoryDto> {
    CategoryDto save(CategoryDto categoryDto);

    List<CategoryDto> findAll();

    List<CategoryDto> findAllPaging(Integer page, Integer size, String sort);

    CategoryDto findById(Long id);

    @Transactional
    CategoryDto add(Long hotelId, CategoryDto categoryDto);

    List<CategoryDto> findCategoriesByHotelId(Long id);
}
