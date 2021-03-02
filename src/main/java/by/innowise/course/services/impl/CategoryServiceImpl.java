package by.innowise.course.services.impl;

import by.innowise.course.dto.entities.CategoryDto;
import by.innowise.course.entities.Category;
import by.innowise.course.facade.AddCategoryToHotelFacade;
import by.innowise.course.mappers.CategoryMapper;
import by.innowise.course.repositories.BaseRepository;
import by.innowise.course.repositories.CategoryRepository;
import by.innowise.course.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final BaseRepository<Category> categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        Category category = CategoryMapper.INSTANCE.categoryDtoToCategory(categoryDto);
        return CategoryMapper.INSTANCE.categoryToCategoryDto(categoryRepository.save(category));
    }
}
