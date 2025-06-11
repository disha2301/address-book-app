package com.gevernova.addressbook.repository;

import com.gevernova.addressbook.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

    @Query(value = "SELECT * FROM address ORDER BY city, state", nativeQuery = true)
    List<Address> findAllSortedByCityAndState();

    @Query(value = "SELECT * FROM address WHERE city = ?1 OR state = ?2", nativeQuery = true)
    List<Address> findByCityOrState(String city, String state);
}
