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

    // http://localhost:8081/api/addresses/user/1
    @PostMapping("/user/{userId}")
    public AddressResponseDTO create(@PathVariable Long userId, @Valid @RequestBody AddressRequestDTO dto) {
        Address address = new Address();
        BeanUtils.copyProperties(dto, address);
        return toResponse(addressService.createAddress(userId, address));
    }

    // http://localhost:8081/api/addresses/user/1
    @PutMapping("/{id}")
    public AddressResponseDTO update(@PathVariable Long id, @Valid @RequestBody AddressRequestDTO dto) {
        Address updated = new Address();
        BeanUtils.copyProperties(dto, updated);
        return toResponse(addressService.updateAddress(id, updated));
    }

    // http://localhost:8081/api/addresses/user/1
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        addressService.deleteAddress(id);
    }

    // http://localhost:8081/api/addresses/sorted
    @GetMapping("/sorted")
    public List<AddressResponseDTO> getSorted() {
        return addressService.getAllSortedByCityAndState()
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    // http://localhost:8081/api/addresses/search?city=London
    @GetMapping("/search")
    public List<AddressResponseDTO> search(@RequestParam(required = false) String city,
                                           @RequestParam(required = false) String state) {
        return addressService.searchByCityOrState(city, state)
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    private AddressResponseDTO toResponse(Address address) {
        AddressResponseDTO dto = new AddressResponseDTO();
        BeanUtils.copyProperties(address, dto);
        return dto;
    }
}
