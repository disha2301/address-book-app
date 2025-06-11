package com.gevernova.addressbook.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AddressRequestDTO {
    @NotBlank
    private String street1;
    private String street2;
    @NotBlank
    private String city;
    @NotBlank
    private String state;
    @NotBlank
    private String country;
    @NotBlank
    private String pincode;
}
