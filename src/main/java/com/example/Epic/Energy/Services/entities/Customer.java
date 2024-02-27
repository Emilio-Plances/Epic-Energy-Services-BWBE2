package com.example.Epic.Energy.Services.entities;

import com.example.Epic.Energy.Services.enums.CustomerType;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "business_name", unique = true)
    private String businessName;
    @Column(name = "vat_number", unique = true)
    private String vatNumber;
    @Column(name = "insertion_date")
    private LocalDate insertionDate;
    @Column(name = "last_contact_date")
    private LocalDate lastContactDate;
    @Column(name = "annual_turnover")
    private double annualTurnover;
    @Column(unique = true)
    private String pec;
    @Column(name = "phone_number", unique = true)
    private String phoneNumber;
    @Column(name = "registered_office_address")
    private String registeredOfficeAddress;
    @Column(name = "operational_headquarters_address")
    private String operationalHeadquartersAddress;
    @Column(name = "contact_name")
    private String contactName;
    @Column(name = "contact_surname")
    private String contactSurname;
    @Column(name = "contact_number", unique = true)
    private String contactNumber;
    private String logo;
    @Column(name = "customer_type")
    @Enumerated(EnumType.STRING)
    private CustomerType customerType;
    @Column(unique = true)
    private String email;
    @OneToOne
    @JoinColumn(name = "address_fk")
    private Address address;
    @OneToMany(mappedBy = "customer")
    private List<Invoice> invoices;
}
