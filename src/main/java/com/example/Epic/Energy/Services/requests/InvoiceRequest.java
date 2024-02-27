package com.example.Epic.Energy.Services.requests;

import com.example.Epic.Energy.Services.entities.Customer;
import com.example.Epic.Energy.Services.enums.InvoiceStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class InvoiceRequest {
    @NotNull(message = "date request")
    private LocalDate date;

    @NotNull(message = "status request")
    private InvoiceStatus status;

    @NotNull(message = "customer request")
    private Long customerId;
}
