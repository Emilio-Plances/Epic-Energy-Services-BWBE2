package com.example.Epic.Energy.Services.entities;

import com.example.Epic.Energy.Services.enums.InvoiceStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "invoices")
@Data

public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private String number;
    @Enumerated(EnumType.STRING)
    private InvoiceStatus status;
    @ManyToOne
    private Customer customer;

    }

