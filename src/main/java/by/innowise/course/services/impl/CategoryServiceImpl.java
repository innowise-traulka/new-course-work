package by.innowise.course.services.impl;

import by.innowise.course.dto.entities.CategoryDto;
import by.innowise.course.dto.entities.ReservationDto;
import by.innowise.course.entities.Category;
import by.innowise.course.entities.Hotel;
import by.innowise.course.exception.ApiException;
import by.innowise.course.mappers.CategoryMapper;
import by.innowise.course.mappers.HotelMapper;
import by.innowise.course.mappers.ReservationMapper;
import by.innowise.course.repositories.BaseRepository;
import by.innowise.course.repositories.CategoryRepository;
import by.innowise.course.services.CategoryService;
import by.innowise.course.services.HotelService;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final HotelService hotelService;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, HotelService hotelService) {
        this.categoryRepository = categoryRepository;
        this.hotelService = hotelService;
    }

    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        Category category = CategoryMapper.INSTANCE.categoryDtoToCategory(categoryDto);
        return CategoryMapper.INSTANCE.categoryToCategoryDto(categoryRepository.save(category));
    }

    @Override
    public List<CategoryDto> findAll() {
        return categoryRepository.findAll().stream().map(CategoryMapper.INSTANCE::categoryToCategoryDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CategoryDto> findAllPaging(Integer page, Integer size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return categoryRepository.findAll().stream().map(CategoryMapper.INSTANCE::categoryToCategoryDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto findById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() ->
                new ApiException("Category not found"));
        return CategoryMapper.INSTANCE.categoryToCategoryDto(category);
    }

    @Transactional
    @Override
    public CategoryDto add(Long hotelId, CategoryDto categoryDto) {
        Hotel hotel = HotelMapper.INSTANCE.hotelDtoToHotel(hotelService.findById(hotelId));
        Category category = CategoryMapper.INSTANCE.categoryDtoToCategory(categoryDto);
        category.setHotel(hotel);
        return save(CategoryMapper.INSTANCE.categoryToCategoryDto(category));
    }

    @Override
    public List<CategoryDto> findCategoriesByHotelId(Long id) {
        return categoryRepository.findCategoriesByHotelId(id).stream().map(CategoryMapper
                .INSTANCE::categoryToCategoryDto)
                .collect(Collectors.toList());
    }
}
