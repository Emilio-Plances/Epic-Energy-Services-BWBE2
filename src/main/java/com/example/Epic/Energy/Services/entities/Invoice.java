package com.example.Epic.Energy.Services.entities;

import com.example.Epic.Energy.Services.enums.InvoiceStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "invoices")
@Data
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long number;
    private LocalDate date;
    @Enumerated(EnumType.STRING)
    private InvoiceStatus status;
    @ManyToOne
    @JoinColumn(name = "customer_fk")
    private Customer customer;
}

