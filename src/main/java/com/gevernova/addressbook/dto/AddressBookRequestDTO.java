package com.gevernova.addressbook.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//DTO for receiving address book entry data from client requests.
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressBookRequestDTO {
    @NotBlank(message = "Name field cannot be empty")
    @Pattern(regexp = "^[A-Z][a-z]{2,}$")
    private String name;

    @NotBlank(message = "Address is mandatory")
    private String address;

    @NotBlank(message = "City is mandatory")
    private String city;

    @NotBlank(message = "State is mandatory")
    private String state;

    @Pattern(regexp = "\\d{5}(-\\{4})?", message = "Zip must be a valid ZIP code")
    private String zip;

    @Pattern(regexp = "\\d{3}-\\d{4}", message = "Phone must be in format XXX-XXXX")
    private String phone;
}
