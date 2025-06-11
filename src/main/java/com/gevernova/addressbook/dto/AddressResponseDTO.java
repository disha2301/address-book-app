package com.gevernova.addressbook.dto;

import lombok.Data;

@Data
public class AddressResponseDTO {
    private Long id;
    private String street1;
    private String street2;
    private String city;
    private String state;
    private String country;
    private String pincode;
}
