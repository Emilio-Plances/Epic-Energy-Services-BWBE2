package com.example.Epic.Energy.Services.entities;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    @Column(name = "street_number")
    private String streetNumber;
    private String city;
    @Column(name = "postal_code")
    private String postalCode;
    private String country;
    private String province;

    @ManyToOne
    @JoinColumn(name = "municipality_fk")
    private Municipality municipality;



}
