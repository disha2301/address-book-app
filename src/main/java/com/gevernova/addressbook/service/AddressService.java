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

    public Address updateAddress(Integer id, Address updatedAddress) {
        Address existing = getAddressById(id);

        existing.setStreet1(updatedAddress.getStreet1());
        existing.setStreet2(updatedAddress.getStreet2());
        existing.setCity(updatedAddress.getCity());
        existing.setState(updatedAddress.getState());
        existing.setCountry(updatedAddress.getCountry());
        existing.setPincode(updatedAddress.getPincode());

        return addressRepository.save(existing);
    }

    public void deleteAddress(Integer id) {
        if (!addressRepository.existsById(id)) {
            throw new EntityNotFoundException("Address not found with ID: " + id);
        }
        addressRepository.deleteById(id);
    }

    public List<Address> getAllSortedByCityAndState() {
        return addressRepository.findAllSortedByCityAndState();
    }

    public List<Address> searchByCityOrState(String city, String state) {
        return addressRepository.findByCityOrState(city, state);
    }

    public Address getAddressById(Integer id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Address not found with ID: " + id));
    }
}
