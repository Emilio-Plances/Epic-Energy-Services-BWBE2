package com.example.Epic.Energy.Services.requests;

import com.example.Epic.Energy.Services.entities.Customer;
import com.example.Epic.Energy.Services.enums.InvoiceStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class InvoiceRequest {

    @NotBlank(message = "date request")
    private LocalDate date;

    @NotBlank(message = "number request")
    private String number;

    @NotBlank(message = "status request")
    private InvoiceStatus status;

    @NotBlank(message = "customer request")
    private Customer customer;

}
