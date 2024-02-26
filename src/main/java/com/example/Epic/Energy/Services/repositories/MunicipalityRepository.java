package com.example.Epic.Energy.Services.repositories;

import com.example.Epic.Energy.Services.entities.Municipality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MunicipalityRepository extends JpaRepository<Municipality,Long>, PagingAndSortingRepository<Municipality,Long> {
}
