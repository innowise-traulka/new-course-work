package by.innowise.course.controllers;

import by.innowise.course.dto.entities.PassportDto;
import by.innowise.course.services.PassportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final PassportService passportService;

    @Autowired
    public UserController(PassportService passportService) {
        this.passportService = passportService;
    }

    @PostMapping("/{id}/passport")
    public ResponseEntity<PassportDto> addPassport(@RequestBody @Valid final PassportDto passportDto,
                                                       @PathVariable final Long id) {
        return ResponseEntity.ok(passportService.addPassportToUser(id, passportDto));
    }
}
