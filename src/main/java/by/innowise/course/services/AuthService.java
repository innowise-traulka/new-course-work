package by.innowise.course.services;

import by.innowise.course.config.security.JwtUtil;
import by.innowise.course.dto.entities.AuthDto;
import by.innowise.course.dto.entities.UserDto;
import by.innowise.course.entities.User;
import by.innowise.course.entities.types.UserStatus;
import by.innowise.course.exception.ApiException;
import by.innowise.course.facade.RegisterFacade;
import by.innowise.course.mappers.UserMapper;
import by.innowise.course.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final RegisterFacade registerFacade;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthService(
            RegisterFacade registerFacade,
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            JwtUtil jwtUtil) {
        this.registerFacade = registerFacade;
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    public UserDto register(UserDto userDto) {
        return registerFacade.register(userDto);
    }

    public UserDto confirm(String code) {
        Optional<User> optionalUser = userRepository.findByConfirmationCode(code);
        if (optionalUser.isEmpty()) {
            throw new ApiException("Code not found");
        }
        User user = optionalUser.get();
        user.setUserStatus(UserStatus.ACTIVE);
        return UserMapper.INSTANCE.userToUserDto(userRepository.save(user));
    }

    public String login(AuthDto authDto) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        authDto.getEmail(), authDto.getPassword()));
        return jwtUtil.generateToken(authDto.getEmail());
    }
}
