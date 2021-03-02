package by.innowise.course.controllers;

import by.innowise.course.dto.entities.PassportDto;
import by.innowise.course.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/{id}/passport")
    public ResponseEntity<PassportDto> addPassport(@RequestBody @Valid final PassportDto passportDto,
                                                       @PathVariable final Long id) {
        return ResponseEntity.ok(userService.addPassportToUser(id, passportDto));
    }
}
