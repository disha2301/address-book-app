package com.gevernova.addressbook.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//DTO for sending address book entry data in API responses.
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressBookResponseDTO {
    private Long id;
    private String name;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String phone;
}
