package by.innowise.course.controllers;

import by.innowise.course.dto.entities.CategoryDto;
import by.innowise.course.dto.entities.HotelDto;
import by.innowise.course.dto.entities.ReservationDto;
import by.innowise.course.dto.entities.RoomDto;
import by.innowise.course.services.CategoryService;
import by.innowise.course.services.HotelService;
import by.innowise.course.services.ReservationService;
import by.innowise.course.services.RoomService;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hotel")
@CrossOrigin(origins = "*")
public class HotelController {
    private final HotelService hotelService;
    private final RoomService roomService;
    private final CategoryService categoryService;
    private final ReservationService reservationService;

    @Autowired
    public HotelController(
            HotelService hotelService, RoomService roomService,
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
    public ResponseEntity<CategoryDto> addCategory(
            @RequestBody @Valid final CategoryDto categoryDto,
            @PathVariable final Long id) {
        return ResponseEntity.ok(categoryService.add(id, categoryDto));
    }

    @PostMapping("/{id}/category/{categoryId}/room")
    public ResponseEntity<RoomDto> addRoom(
            @RequestBody @Valid final RoomDto roomDto,
            @PathVariable final Long id,
            @PathVariable final Long categoryId) {
        return ResponseEntity.ok(roomService.add(id, categoryId, roomDto));
    }

    @PostMapping("/{id}/room/{roomId}/reservation")
    public ResponseEntity<ReservationDto> addReservation(
            @RequestBody @Valid final ReservationDto reservationDto,
            @PathVariable final Long id,
            @PathVariable final Long roomId) {
        return ResponseEntity.ok(reservationService.add(id, roomId, reservationDto));
    }

    @GetMapping
    public ResponseEntity<List<HotelDto>> findAll() {
        return ResponseEntity.ok(hotelService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HotelDto> findById(@PathVariable final Long id) {
        return ResponseEntity.ok(hotelService.findById(id));
    }

    @GetMapping("/{id}/categories")
    public ResponseEntity<List<CategoryDto>> findByHotelId(@PathVariable final Long id) {
        return ResponseEntity.ok(categoryService.findCategoriesByHotelId(id));
    }
}
