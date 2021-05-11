package by.innowise.course.services.impl;

import by.innowise.course.config.security.JwtUtil;
import by.innowise.course.dto.entities.UserDto;
import by.innowise.course.entities.User;
import by.innowise.course.entities.UserRole;
import by.innowise.course.entities.types.UserRoleName;
import by.innowise.course.entities.types.UserStatus;
import by.innowise.course.exception.ApiException;
import by.innowise.course.mappers.UserMapper;
import by.innowise.course.repositories.UserRepository;
import by.innowise.course.services.UserService;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Autowired
    public UserServiceImpl(
            UserRepository userRepository,
            BCryptPasswordEncoder passwordEncoder,
            JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public UserDto save(UserDto userDto) {
        User user = UserMapper.INSTANCE.userDtoToUser(userDto);
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new ApiException("Email is already taken");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return UserMapper.INSTANCE.userToUserDto(userRepository.save(user));
    }

    @Override
    public UserDto findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new ApiException("User not found"));
        return UserMapper.INSTANCE.userToUserDto(user);
    }

    @Transactional
    @Override
    public UserDto block(Long id) { // TODO: 03/03/2021 validator is blocked?
        User user = UserMapper.INSTANCE.userDtoToUser(findById(id));
        user.setUserStatus(UserStatus.BLOCKED);
        return UserMapper.INSTANCE.userToUserDto(userRepository.save(user));
    }

    @Transactional
    @Override
    public UserDto unblock(Long id) {
        User user = UserMapper.INSTANCE.userDtoToUser(findById(id));
        user.setUserStatus(UserStatus.ACTIVE);
        return UserMapper.INSTANCE.userToUserDto(userRepository.save(user));
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(UserMapper.INSTANCE::userToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("User with email " + email + "not found"));
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.getAuthorities());
    }

    @Override
    public UserDto findByToken(String token) {
        String email = jwtUtil.extractUsername(token);
        return UserMapper.INSTANCE.userToUserDto(userRepository
                .findByEmail(email)
                .orElseThrow(() -> new ApiException("User not found by token")));
    }

    @Override
    public UserDto makeAdmin(Long id) {
        UserDto userDto = findById(id);
        User user = UserMapper.INSTANCE.userDtoToUser(userDto);
        UserRole role = new UserRole();
        role.setName(UserRoleName.ADMIN);
        user.setRole(role);
        return UserMapper.INSTANCE.userToUserDto(userRepository.save(user));
    }
}
