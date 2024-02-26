package com.example.Epic.Energy.Services.entities;

import com.example.Epic.Energy.Services.enums.Municipality;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String street;
    private String streetNumber;
    private String city;
    private String postalCode;
    private String country;
    private Municipality municipality;
    @OneToOne
    private Customer customer;




}
