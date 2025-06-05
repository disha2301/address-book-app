package com.gevernova.addressbook.repository;

import com.gevernova.addressbook.entity.AddressBookEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressBookRepository extends JpaRepository<AddressBookEntry, Long> {}
