package com.example.Epic.Energy.Services.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddressRequest {
    @NotBlank(message = "street request")
    private String street;

    @NotBlank(message = "street number request")
    private String streetNumber;

    @NotBlank(message = "province request")
    private String province;

    @NotBlank(message = "city request")
    private String city;

    private String postalCode;

    @NotBlank(message = "country request")
    private String country;

}
