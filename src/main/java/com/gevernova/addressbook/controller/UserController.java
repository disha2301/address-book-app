package com.gevernova.addressbook.controller;

import com.gevernova.addressbook.entity.User;
import com.gevernova.addressbook.exceptionhandler.EntryNotFoundException;
import com.gevernova.addressbook.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;


    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser);
    }

    // http://localhost:8081/api/users
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // http://localhost:8081/api/users/{id}
    @GetMapping("/{id}")
    public User getById(@PathVariable Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntryNotFoundException("User not found with ID: " + id));
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @Valid @RequestBody User updatedUser) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new EntryNotFoundException("User not found with ID: " + id));

        BeanUtils.copyProperties(updatedUser, existingUser, "id");
        return userRepository.save(existingUser);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }
}