package com.gevernova.addressbook.repository;

import com.gevernova.addressbook.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query(value = "SELECT * FROM addresses ORDER BY city ASC, state ASC", nativeQuery = true)
    List<Address> findAllSortedByCityAndState();

    @Query(value = "SELECT * FROM addresses WHERE (:city IS NULL OR city = :city) OR (:state IS NULL OR state = :state)", nativeQuery = true)
    List<Address> searchByCityOrState(String city, String state);
}
