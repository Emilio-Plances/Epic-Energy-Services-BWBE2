package com.example.Epic.Energy.Services.entities;

import com.example.Epic.Energy.Services.enums.Municipality;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="addresses")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Address {
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
