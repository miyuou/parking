package org.example.reservationservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.reservationservice.domain.dto.user.UserCreateUpdateDTO;
import org.example.reservationservice.domain.dto.user.UserReadDTO;
import org.example.reservationservice.domain.entities.User;
import org.example.reservationservice.domain.mappers.UserMapper;
import org.example.reservationservice.domain.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    public ResponseEntity<UserReadDTO> createUser(@Valid @RequestBody UserCreateUpdateDTO dto) {
        User user = userMapper.toEntity(dto);
        User created = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userMapper.toReadDTO(created));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserReadDTO> getUser(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(userMapper::toReadDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<UserReadDTO>> getAllUsers() {
        List<UserReadDTO> users = userService.getAllUsers()
                .stream()
                .map(userMapper::toReadDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserReadDTO> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UserCreateUpdateDTO dto) {

        User existingUser = userService.getUserById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        // FIXED: Use camelCase method names
        existingUser.setFirstName(dto.getFirstName());
        existingUser.setLastName(dto.getLastName());
        existingUser.setPhone(dto.getPhone());

        User updated = userService.updateUser(id, existingUser);
        return ResponseEntity.ok(userMapper.toReadDTO(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}