package by.innowise.course.controllers;

import by.innowise.course.dto.entities.CategoryDto;
import by.innowise.course.dto.entities.HotelDto;
import by.innowise.course.dto.entities.RoomDto;
import by.innowise.course.services.CategoryService;
import by.innowise.course.services.HotelService;
import by.innowise.course.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/hotel")
public class HotelController {
    private final HotelService hotelService;
    private final RoomService roomService;
    private final CategoryService categoryService;

    @Autowired
    public HotelController(HotelService hotelService, RoomService roomService, CategoryService categoryService) {
        this.hotelService = hotelService;
        this.roomService = roomService;
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<HotelDto> addCategory(@RequestBody @Valid final HotelDto hotelDto) {
        return ResponseEntity.ok(hotelService.save(hotelDto));
    }

    @PostMapping("/{id}/category")
    public ResponseEntity<CategoryDto> addCategory(@RequestBody @Valid final CategoryDto categoryDto,
                                                   @PathVariable final Long id) {
        return ResponseEntity.ok(categoryService.add(id, categoryDto));
    }

    @PostMapping("/{id}/category/{categoryId}/room")
    public ResponseEntity<RoomDto> addRoom(@RequestBody @Valid final RoomDto roomDto,
                                           @PathVariable final Long id,
                                           @PathVariable final Long categoryId) {
        return ResponseEntity.ok(roomService.add(id, categoryId, roomDto));
    }

}
