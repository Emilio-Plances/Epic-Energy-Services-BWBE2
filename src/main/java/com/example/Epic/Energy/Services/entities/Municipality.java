package com.example.Epic.Energy.Services.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name="municipalities")
public class Municipality {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String province;
    @Column(name = "province_abbr")
    private String provinceAbbr;
    private String region;
}
