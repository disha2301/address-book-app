package com.gevernova.addressbook.repository;

import com.gevernova.addressbook.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findAllByOrderByCityAsc();
    List<Address> findAllByOrderByStateAsc();

    @Query(value = "SELECT * FROM address WHERE city = ?1", nativeQuery = true)
    List<Address> findByCity(String city);

    @Query(value = "SELECT * FROM address WHERE state = ?1", nativeQuery = true)
    List<Address> findByState(String state);
}
