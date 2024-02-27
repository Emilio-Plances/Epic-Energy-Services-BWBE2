package com.example.Epic.Energy.Services.repositories;

import com.example.Epic.Energy.Services.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long>, PagingAndSortingRepository<Customer,Long> {
}
