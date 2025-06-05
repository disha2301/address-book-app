package com.gevernova.addressbook.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "address_book_entries")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressBookEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String phone;
}
