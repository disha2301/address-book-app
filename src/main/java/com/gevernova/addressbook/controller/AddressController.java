package com.gevernova.addressbook.controller;

import com.gevernova.addressbook.dto.AddressRequestDTO;
import com.gevernova.addressbook.dto.AddressResponseDTO;
import com.gevernova.addressbook.entity.Address;
import com.gevernova.addressbook.service.AddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/addresses")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    // POST - http://localhost:8081/api/addresses/user/1
    @PostMapping("/user/{userId}")
    public AddressResponseDTO create(@PathVariable Long userId, @Valid @RequestBody AddressRequestDTO dto) {
        Address address = new Address();
        BeanUtils.copyProperties(dto, address);
        return toResponse(addressService.createAddress(userId, address));
    }
    // PUT - http://localhost:8081/api/addresses/user/1
    @PutMapping("/{id}")
    public AddressResponseDTO update(@PathVariable Long id, @Valid @RequestBody AddressRequestDTO dto) {
        Address updated = new Address();
        BeanUtils.copyProperties(dto, updated);
        return toResponse(addressService.updateAddress(id, updated));
    }
    // DELETE - http://localhost:8081/api/addresses/user/1
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        addressService.deleteAddress(id);
    }
    // GET - http://localhost:8081/api/addresses/sorted/city
    @GetMapping("/sorted/city")
    public List<AddressResponseDTO> getSortedByCity() {
        return addressService.getAllSortedByCity().stream().map(this::toResponse).collect(Collectors.toList());
    }
    // GET - http://localhost:8081/api/addresses/sorted/state
    @GetMapping("/sorted/state")
    public List<AddressResponseDTO> getSortedByState() {
        return addressService.getAllSortedByState().stream().map(this::toResponse).collect(Collectors.toList());
    }

    @GetMapping("/search/city")
    public List<AddressResponseDTO> searchByCity(@RequestParam String city) {
        return addressService.searchByCity(city).stream().map(this::toResponse).collect(Collectors.toList());
    }

    @GetMapping("/search/state")
    public List<AddressResponseDTO> searchByState(@RequestParam String state) {
        return addressService.searchByState(state).stream().map(this::toResponse).collect(Collectors.toList());
    }

    private AddressResponseDTO toResponse(Address address) {
        AddressResponseDTO dto = new AddressResponseDTO();
        BeanUtils.copyProperties(address, dto);
        return dto;
    }
}
