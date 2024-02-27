package com.example.Epic.Energy.Services.repositories;

import com.example.Epic.Energy.Services.entities.Municipality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface MunicipalityRepository extends JpaRepository<Municipality,Long>, PagingAndSortingRepository<Municipality,Long> {
    Optional<Municipality> findByName(String name);
}
