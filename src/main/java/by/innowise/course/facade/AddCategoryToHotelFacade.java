package by.innowise.course.facade;

import by.innowise.course.dto.entities.CategoryDto;
import by.innowise.course.entities.Category;
import by.innowise.course.entities.Hotel;
import by.innowise.course.entities.types.CategoryName;
import by.innowise.course.mappers.CategoryMapper;
import by.innowise.course.mappers.HotelMapper;
import by.innowise.course.services.CategoryService;
import by.innowise.course.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AddCategoryToHotelFacade {
    private final HotelService hotelService;
    private final CategoryService categoryService;

    @Autowired
    public AddCategoryToHotelFacade(HotelService hotelService, CategoryService categoryService) {
        this.hotelService = hotelService;
        this.categoryService = categoryService;
    }

    @Transactional
    public CategoryDto add(Long hotelId, CategoryDto categoryDto) {
        Hotel hotel = HotelMapper.INSTANCE.hotelDtoToHotel(hotelService.findById(hotelId));
        Category category = CategoryMapper.INSTANCE.categoryDtoToCategory(categoryDto);
        category.setHotel(hotel);
        return CategoryMapper.INSTANCE.categoryToCategoryDto(categoryService.save(category));
    }
}
