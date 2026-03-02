package com.coreerp.coreerp.controller;

import com.coreerp.coreerp.dto.UserRequestDto;
import com.coreerp.coreerp.dto.UserResponseDto;
import com.coreerp.coreerp.entity.User;
import com.coreerp.coreerp.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // PUBLIC REGISTRATION
    @PostMapping
    public UserResponseDto createUser(@Valid @RequestBody UserRequestDto dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword()); // later we hash it
        user.setRole(dto.getRole());

        User savedUser = userRepository.save(user);

        return new UserResponseDto(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getRole());
    }

    // SECURED - FETCH ALL USERS
    @GetMapping
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(u -> new UserResponseDto(
                        u.getId(),
                        u.getUsername(),
                        u.getRole()))
                .toList();
    }
}
