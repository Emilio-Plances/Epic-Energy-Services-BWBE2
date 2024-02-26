package com.example.Epic.Energy.Services.requests;


import com.example.Epic.Energy.Services.entities.Customer;
import com.example.Epic.Energy.Services.enums.Municipality;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddressRequest {

    @NotBlank(message = "Il campo street non può essere vuoto")
    private String street;

    @NotBlank(message = "Il campo streetNumber non può essere vuoto")
    private String streetNumber;

    @NotBlank(message = "Il campo city non può essere vuoto")
    private String city;

    @NotBlank(message = "Il campo postalCode non può essere vuoto")
    private String postalCode;

    @NotBlank(message = "Il campo country non può essere vuoto")
    private String country;

    @NotNull(message = "Il campo municipality non può essere vuoto")
    private Municipality municipality;

    private Customer customer;
}
