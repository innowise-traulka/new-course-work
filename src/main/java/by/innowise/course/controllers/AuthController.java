package by.innowise.course.controllers;

import by.innowise.course.dto.entities.UserDto;
import by.innowise.course.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@Valid @RequestBody final UserDto userDto) {
        return ResponseEntity.ok(authService.register(userDto));
    }

    @GetMapping("/confirm/{code}")
    public ResponseEntity<UserDto> confirmRegister(@PathVariable final String code) {
        return ResponseEntity.ok(authService.confirm(code));
    }
}
