package com.gevernova.addressbook.service;

import com.gevernova.addressbook.entity.Address;
import com.gevernova.addressbook.entity.User;
import com.gevernova.addressbook.repository.AddressRepository;
import com.gevernova.addressbook.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    public Address createAddress(Long userId, Address address) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));
        address.setUser(user);
        return addressRepository.save(address);
    }

    public Address updateAddress(Long id, Address updatedAddress) {
        Address existing = addressRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Address not found"));
        updatedAddress.setId(existing.getId());
        updatedAddress.setUser(existing.getUser());
        return addressRepository.save(updatedAddress);
    }

    public void deleteAddress(Long id) {
        if (!addressRepository.existsById(id)) {
            throw new EntityNotFoundException("Address not found");
        }
        addressRepository.deleteById(id);
    }

    public List<Address> getAllSortedByCityAndState() {
        return addressRepository.findAllSortedByCityAndState();
    }

    public List<Address> searchByCityOrState(String city, String state) {
        return addressRepository.searchByCityOrState(city, state);
    }
}
