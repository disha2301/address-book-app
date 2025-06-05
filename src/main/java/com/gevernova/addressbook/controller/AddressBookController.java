package com.gevernova.addressbook.controller;

import com.gevernova.addressbook.dto.AddressBookRequestDTO;
import com.gevernova.addressbook.dto.AddressBookResponseDTO;
import com.gevernova.addressbook.service.AddressBookService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addressbook")
public class AddressBookController {

    private static Logger log = LoggerFactory.getLogger(AddressBookController.class);
    private AddressBookService service;

    // Inject AddressBookService using constructor injection
    public AddressBookController(AddressBookService service) {
        this.service = service;
    }

    // Create a new address book entry
    @PostMapping
    // @Valid for validating the input field
    public ResponseEntity<AddressBookResponseDTO> create(@Valid @RequestBody AddressBookRequestDTO dto) {
        log.info("Creating new address book entry for name: {}", dto.getName());
        AddressBookResponseDTO response = service.addEntry(dto);
        log.debug("Created entry: {}", response);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Get all address book entries
    @GetMapping
    public ResponseEntity<List<AddressBookResponseDTO>> getAll() {
        log.info("Fetching all address book entries");
        return ResponseEntity.ok(service.getAll());
    }

    // Get an entry by ID
    @GetMapping("/{id}")
    public ResponseEntity<AddressBookResponseDTO> getById(@PathVariable Long id) {
        log.info("Fetching address book entry with ID: {}", id);
        return ResponseEntity.ok(service.getById(id));
    }

    // Update an existing entry by ID
    @PutMapping("/{id}")
    // @Valid for validating the input field
    public ResponseEntity<AddressBookResponseDTO> update(@PathVariable Long id,
                                                         @Valid @RequestBody AddressBookRequestDTO dto) {
        log.info("Updating address book entry with ID: {}", id);
        AddressBookResponseDTO updated = service.updateEntry(id, dto);
        log.debug("Updated entry: {}", updated);
        return ResponseEntity.ok(updated);
    }

    // Delete an entry by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        log.info("Deleting address book entry with ID: {}", id);
        service.deleteById(id);
        return ResponseEntity.ok("Deleted entry with ID: " + id);
    }
}
