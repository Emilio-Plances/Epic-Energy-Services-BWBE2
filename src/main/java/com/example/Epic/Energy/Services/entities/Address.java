package com.example.Epic.Energy.Services.entities;

import com.example.Epic.Energy.Services.enums.Municipality;
import jakarta.persistence.*;

public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String street;
    @Column(name = "street_number")
    private String streetNumber;
    private String city;
    @Column(name = "postal_code")
    private String postalCode;
    private String country;
    private Municipality municipality;
    @OneToOne
    private Customer customer;




}
