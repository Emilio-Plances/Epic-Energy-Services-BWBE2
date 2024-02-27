package com.example.Epic.Energy.Services.repositories;

import com.example.Epic.Energy.Services.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice,Long>, PagingAndSortingRepository<Invoice,Long> {
    Optional<Invoice> findByNumber(long number);
}
