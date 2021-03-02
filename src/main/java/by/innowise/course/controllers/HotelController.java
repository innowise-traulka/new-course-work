package by.innowise.course.controllers;

import by.innowise.course.dto.entities.CategoryDto;
import by.innowise.course.dto.entities.HotelDto;
import by.innowise.course.facade.AddCategoryToHotelFacade;
import by.innowise.course.services.CategoryService;
import by.innowise.course.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/hotel")
public class HotelController {
    private final HotelService hotelService;
    private final AddCategoryToHotelFacade addCategoryToHotelFacade;

    @Autowired
    public HotelController(HotelService hotelService, AddCategoryToHotelFacade addCategoryToHotelFacade) {
        this.hotelService = hotelService;
        this.addCategoryToHotelFacade = addCategoryToHotelFacade;
    }

    @PostMapping
    public ResponseEntity<HotelDto> addCategory(@RequestBody @Valid final HotelDto hotelDto) {
        return ResponseEntity.ok(hotelService.save(hotelDto));
    }

    @PostMapping("/{id}/category")
    public ResponseEntity<CategoryDto> addCategory(@RequestBody @Valid final CategoryDto categoryDto,
                                                   @PathVariable final Long id) {
        return ResponseEntity.ok(addCategoryToHotelFacade.add(id, categoryDto));
    }

}
