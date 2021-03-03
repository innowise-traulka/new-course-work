package by.innowise.course.controllers;

import by.innowise.course.dto.entities.CategoryDto;
import by.innowise.course.dto.entities.HotelDto;
import by.innowise.course.dto.entities.ReservationDto;
import by.innowise.course.dto.entities.RoomDto;
import by.innowise.course.services.CategoryService;
import by.innowise.course.services.HotelService;
import by.innowise.course.services.ReservationService;
import by.innowise.course.services.RoomService;
import by.innowise.course.validator.ValidationError;
import by.innowise.course.validator.ValidationErrorBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/hotel")
public class HotelController {
    private final HotelService hotelService;
    private final RoomService roomService;
    private final CategoryService categoryService;
    private final ReservationService reservationService;

    @Autowired
    public HotelController(HotelService hotelService, RoomService roomService,
                           CategoryService categoryService, ReservationService reservationService) {
        this.hotelService = hotelService;
        this.roomService = roomService;
        this.categoryService = categoryService;
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity<HotelDto> addHotel(@Valid @RequestBody final HotelDto hotelDto) {
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

    @PostMapping("/{id}/room/{roomId}/reservation")
    public ResponseEntity<ReservationDto> addReservation(@RequestBody @Valid final ReservationDto reservationDto,
                                                         @PathVariable final Long id,
                                                         @PathVariable final Long roomId) {
        return ResponseEntity.ok(reservationService.add(id, roomId, reservationDto));
    }
}
