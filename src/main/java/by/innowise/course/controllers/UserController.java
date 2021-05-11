package by.innowise.course.controllers;

import by.innowise.course.dto.entities.PassportDto;
import by.innowise.course.dto.entities.UserDto;
import by.innowise.course.services.PassportService;
import by.innowise.course.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")
public class UserController {
    private final PassportService passportService;
    private final UserService userService;

    @Autowired
    public UserController(PassportService passportService, UserService userService) {
        this.passportService = passportService;
        this.userService = userService;
    }

    @PostMapping("/{id}/passport")
    public ResponseEntity<PassportDto> addPassport(@RequestBody @Valid final PassportDto passportDto,
                                                       @PathVariable final Long id) {
        return ResponseEntity.ok(passportService.addPassportToUser(id, passportDto));
    }

    @PutMapping("/{id}/block")
    public ResponseEntity<UserDto> block(@PathVariable final Long id) {
        return ResponseEntity.ok(userService.block(id));
    }

    @PutMapping("/{id}/unblock")
    public ResponseEntity<UserDto> unblock(@PathVariable final Long id) {
        return ResponseEntity.ok(userService.unblock(id));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable final Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @GetMapping("/token/{token}")
    public ResponseEntity<UserDto> findByToken(@PathVariable final String token) {
        return ResponseEntity.ok(userService.findByToken(token));
    }

    @PutMapping("/{id}/admin")
    public ResponseEntity<UserDto> makeAdmin(@PathVariable final Long id) {
        return ResponseEntity.ok(userService.makeAdmin(id));
    }
}
