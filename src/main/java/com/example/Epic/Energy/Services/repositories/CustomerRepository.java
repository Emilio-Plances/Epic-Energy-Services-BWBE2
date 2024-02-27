package com.example.Epic.Energy.Services.repositories;
import com.example.Epic.Energy.Services.entities.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDate;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    //Nome
    // Recupera tutti i clienti ordinati per ragione sociale in ordine ascendente
    @Query("SELECT c FROM Customer c ORDER BY c.businessName ASC")
    Page<Customer> findBusinessNameASC(Pageable pageable);

    //FatturatoAnnuale
    // Recupera tutti i clienti ordinati per fatturato annuale in ordine discendente
    @Query("SELECT c FROM Customer c ORDER BY c.annualTurnover DESC")
    Page<Customer> findAllOrderByFatturatoAnnualeDesc(Pageable pageable);

    //Data di inserimento
    // Recupera tutti i clienti ordinati per data di inserimento in ordine discendente
    @Query("SELECT c FROM Customer c ORDER BY c.insertionDate DESC")
    Page<Customer> findAllOrderByDataInserimentoDesc(Pageable pageable);

    //Data di ultimo contatto
    // Recupera tutti i clienti ordinati per data di ultimo contatto in ordine discendente
    @Query("SELECT c FROM Customer c ORDER BY c.lastContactDate DESC")
    Page<Customer> findAllOrderByDataUltimoContattoDesc(Pageable pageable);

    //Provincia della sede legale
    // Recupera tutti i clienti ordinati per provincia della sede legale in ordine ascendente
    @Query("SELECT c FROM Customer c ORDER BY c.address.province ASC")
    Page<Customer> findAllOrderByProvinciaSedeLegaleAsc(Pageable pageable);

    //Fatturato annuale
    //Nel dubbio ho messo il fatturoAnnuale come un lavore intermezzo a dei valori. Si può creare anche mettendo direttamente un prezzo
    // Recupera i clienti con un fatturato annuale compreso tra i valori specificati
    @Query("SELECT c FROM Customer c WHERE c.annualTurnover BETWEEN :minRevenue AND :maxRevenue")
    Page<Customer> findByFatturatoAnnualeBetween(double minRevenue, double maxRevenue, Pageable pageable);

    // Fatturato annuale
    @Query("SELECT c FROM Customer c WHERE c.annualTurnover = :revenue")
    Page<Customer> findByFatturatoAnnuale(double revenue, Pageable pageable);


    //Data di inserimento
    // Recupera i clienti con data di inserimento compresa tra le date specificate
    @Query("SELECT c FROM Customer c WHERE c.insertionDate BETWEEN :fromDate AND :toDate")
    Page<Customer> findByDataInserimentoBetween(LocalDate fromDate, LocalDate toDate, Pageable pageable);

    // Data di inserimento
    @Query("SELECT c FROM Customer c WHERE c.insertionDate = :date")
    Page<Customer> findByDataInserimento(LocalDate date, Pageable pageable);

    //Data di ultimo contatto
    // Recupera i clienti con data di ultimo contatto compresa tra le date specificate
    @Query("SELECT c FROM Customer c WHERE c.lastContactDate BETWEEN :fromDate AND :toDate")
    Page<Customer> findByDataUltimoContattoBetween(LocalDate fromDate, LocalDate toDate, Pageable pageable);

    // Data di ultimo contatto
    @Query("SELECT c FROM Customer c WHERE c.lastContactDate = :date")
    Page<Customer> findByDataUltimoContatto(LocalDate date, Pageable pageable);


    //Parte del nome
    // Recupera i clienti il cui nome contiene una determinata stringa (case insensitive)
    @Query("SELECT c FROM Customer c WHERE LOWER(c.businessName) LIKE LOWER(CONCAT('%', :name, '%'))")
    Page<Customer> findByRagioneSocialeContaining(String name, Pageable pageable);
}
