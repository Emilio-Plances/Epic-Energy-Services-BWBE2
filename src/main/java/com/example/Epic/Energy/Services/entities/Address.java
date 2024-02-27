package com.example.Epic.Energy.Services.entities;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "addresses")
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
    @OneToOne
    @JoinColumn(name = "municipality_fk")
    private Municipality municipality;
    @OneToOne(mappedBy = "address")
    private Customer customer;
}
