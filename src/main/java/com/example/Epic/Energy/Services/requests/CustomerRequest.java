package com.example.Epic.Energy.Services.requests;

import com.example.Epic.Energy.Services.enums.CustomerType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CustomerRequest {
    @NotBlank(message = "Business name request")
    private String businessName;

    @NotBlank(message = "Vat number request")
    private String vatNumber;

    @NotBlank(message = "Pec request")
    @Email
    private String pec;

    @NotBlank(message = "Phone number request")
    private String phoneNumber;

    @NotBlank(message = "Registered office address request")
    private String registeredOfficeAddress;

    @NotBlank(message = "Operational headquarters address request")
    private String operationalHeadquartersAddress;

    @NotBlank(message = "Contact name request")
    private String contactName;

    @NotBlank(message = "Contact surname request")
    private String contactSurname;

    @NotBlank(message = "Contact number request")
    private String contactNumber;

    @NotNull(message = "Customer type request")
    private CustomerType customerType;

    @NotBlank(message = "Email request")
    @Email
    private String email;
}
