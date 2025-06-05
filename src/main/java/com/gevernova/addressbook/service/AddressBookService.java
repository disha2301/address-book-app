package com.gevernova.addressbook.service;

import com.gevernova.addressbook.dto.AddressBookRequestDTO;
import com.gevernova.addressbook.dto.AddressBookResponseDTO;
import com.gevernova.addressbook.entity.AddressBookEntry;
import com.gevernova.addressbook.exceptionhandler.EntryNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class AddressBookService {
    // In-memory storage for address book entries
    private List<AddressBookEntry> addressBook = new ArrayList<>();
    private AtomicLong counter = new AtomicLong(1); // To generate unique IDs

    // Convert Request DTO to Entity, assign unique ID
    private AddressBookEntry mapToEntity(AddressBookRequestDTO dto) {
        return AddressBookEntry.builder()
                .id(counter.getAndIncrement())
                .name(dto.getName())
                .address(dto.getAddress())
                .city(dto.getCity())
                .state(dto.getState())
                .zip(dto.getZip())
                .phone(dto.getPhone())
                .build();
    }

    // Convert Entity to Response DTO
    private AddressBookResponseDTO mapToDTO(AddressBookEntry entry) {
        return AddressBookResponseDTO.builder()
                .id(entry.getId())
                .name(entry.getName())
                .address(entry.getAddress())
                .city(entry.getCity())
                .state(entry.getState())
                .zip(entry.getZip())
                .phone(entry.getPhone())
                .build();
    }

    // Add new entry to address book
    public AddressBookResponseDTO addEntry(AddressBookRequestDTO dto) {
        AddressBookEntry entry = mapToEntity(dto);
        addressBook.add(entry);
        return mapToDTO(entry);
    }

    // Retrieve all entries
    public List<AddressBookResponseDTO> getAll() {
        return addressBook.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Get entry by ID, throws if not found
    public AddressBookResponseDTO getById(Long id) {
        return addressBook.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .map(this::mapToDTO)
                .orElseThrow(() -> new EntryNotFoundException("Entry not found with id: " + id));
    }

    // Update existing entry by ID, throws if not found
    public AddressBookResponseDTO updateEntry(Long id, AddressBookRequestDTO dto) {
        AddressBookEntry entry = addressBook.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Entry not found with id: " + id));

        entry.setName(dto.getName());
        entry.setAddress(dto.getAddress());
        entry.setCity(dto.getCity());
        entry.setState(dto.getState());
        entry.setZip(dto.getZip());
        entry.setPhone(dto.getPhone());

        return mapToDTO(entry);
    }

    // Delete entry by ID
    public void deleteById(Long id) {
        addressBook.removeIf(e -> e.getId().equals(id));
    }
}
