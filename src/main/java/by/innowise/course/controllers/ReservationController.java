package by.innowise.course.controllers;

import by.innowise.course.dto.entities.ReservationDto;
import by.innowise.course.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reservation")
@CrossOrigin(origins = "*")
public class ReservationController {
    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<ReservationDto> cancel(@PathVariable final Long id) {
        return ResponseEntity.ok(reservationService.cancel(id));
    }
}
