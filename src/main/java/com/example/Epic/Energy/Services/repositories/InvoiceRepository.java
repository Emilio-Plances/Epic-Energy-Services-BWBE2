package com.example.Epic.Energy.Services.repositories;
import com.example.Epic.Energy.Services.entities.Customer;
import com.example.Epic.Energy.Services.entities.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long>, PagingAndSortingRepository<Invoice, Long> {


    // Recupera un'istanza di Invoice in base al numero specificato
    Optional<Invoice> findByNumber(long number);

    //Cliente
    // Recupera le fatture relative a un cliente specificato
    @Query("SELECT i FROM Invoice i WHERE i.customer.id = :customerId")
    Page<Invoice> findByCustomer(@Param("customerId") long customerId, Pageable pageable);


    //Stato
    // Recupera le fatture con uno stato specifico
    @Query("SELECT i FROM Invoice i WHERE i.status = :status")
    Page<Invoice> findByStatus(String status, Pageable pageable);

    //Data
    // Recupera le fatture emesse in una data specifica
    @Query("SELECT i FROM Invoice i WHERE i.date = :date")
    Page<Invoice> findByDate(LocalDate date, Pageable pageable);

    // Anno
    @Query("SELECT i FROM Invoice i WHERE YEAR(i.date) = :year")
    Page<Invoice> findByAnno(int year, Pageable pageable);

    //Anno
    // Recupera le fatture emesse tra due date specificate
    @Query("SELECT i FROM Invoice i WHERE i.date BETWEEN :startDate AND :endDate")
    Page<Invoice> findByDateBetween(LocalDate startDate, LocalDate endDate, Pageable pageable);

    //Range di importi
    // Recupera le fatture con un importo compreso tra due valori specificati
    @Query("SELECT i FROM Invoice i WHERE i.amount BETWEEN :minAmount AND :maxAmount")
    Page<Invoice> findByImportoBetween(double minAmount, double maxAmount, Pageable pageable);
}
