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
        Address existing = getAddressById(id);

        existing.setStreet1(updatedAddress.getStreet1());
        existing.setStreet2(updatedAddress.getStreet2());
        existing.setCity(updatedAddress.getCity());
        existing.setState(updatedAddress.getState());
        existing.setCountry(updatedAddress.getCountry());
        existing.setPincode(updatedAddress.getPincode());

        return addressRepository.save(existing);
    }

    public void deleteAddress(Long id) {
        if (!addressRepository.existsById(id)) {
            throw new EntityNotFoundException("Address not found with ID: " + id);
        }
        addressRepository.deleteById(id);
    }

    public List<Address> getAllSortedByCity() {
        return addressRepository.findAllByOrderByCityAsc();
    }

    public List<Address> getAllSortedByState() {
        return addressRepository.findAllByOrderByStateAsc();
    }

    public List<Address> searchByCity(String city) {
        return addressRepository.findByCity(city);
    }

    public List<Address> searchByState(String state) {
        return addressRepository.findByState(state);
    }

    public Address getAddressById(Long id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Address not found with ID: " + id));
    }
}

