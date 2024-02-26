package com.example.Epic.Energy.Services.requests;

import com.example.Epic.Energy.Services.enums.CustomerType;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class CustomerRequest {
    @NotBlank(message = "business name request")
    private String businessName;

    @NotBlank(message = "vat number request")
    private String vatNumber;

    @NotBlank(message = "pec request")
    @Email
    private String pec;

    @NotBlank(message = "phone number request")
    private String phoneNumber;

    @NotBlank(message = "registered office address request")
    private String registeredOfficeAddress;

    @NotBlank(message = "operational headquarters address request")
    private String operationalHeadquartersAddress;

    @NotBlank(message = "contact name request")
    private String contactName;

    @NotBlank(message = "contact surname request")
    private String contactSurname;

    @NotBlank(message = "contact number request")
    private String contactNumber;

    @NotBlank(message = "customer type request")
    private CustomerType customerType;

    @NotBlank(message = "email request")
    @Email
    private String email;
}
