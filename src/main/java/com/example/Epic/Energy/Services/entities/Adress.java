package com.example.Epic.Energy.Services.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

public class Adress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String street;
    private String streetNumber;
    private String city;
    private String postalCode;
    private String country;
    @ManyToOne
    private Municipality municipality;
    @ManyToOne
    private Customer customer;




}