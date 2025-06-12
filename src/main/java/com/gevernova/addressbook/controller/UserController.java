package com.gevernova.addressbook.controller;

import com.gevernova.addressbook.entity.User;
import com.gevernova.addressbook.exceptionhandler.EntryNotFoundException;
import com.gevernova.addressbook.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    // http://localhost:8081/api/users
    @PostMapping
    public User createUser(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }

    // http://localhost:8081/api/users
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // http://localhost:8081/api/users/1
    @GetMapping("/{id}")
    public User getById(@PathVariable Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntryNotFoundException("User not found"));
    }

    // http://localhost:8081/api/users/1
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @Valid @RequestBody User updatedUser) {
        User existing = userRepository.findById(id)
                .orElseThrow(() -> new EntryNotFoundException("User not found"));
        updatedUser.setId(existing.getId());
        return userRepository.save(updatedUser);
    }

    // http://localhost:8081/api/users/1
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }
}
