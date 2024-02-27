package com.example.Epic.Energy.Services.services;

import com.example.Epic.Energy.Services.entities.Customer;
import com.example.Epic.Energy.Services.entities.Invoice;
import com.example.Epic.Energy.Services.exceptions.NotFoundException;
import com.example.Epic.Energy.Services.repositories.InvoiceRepository;
import com.example.Epic.Energy.Services.requests.InvoiceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private CustomerService customerService;

    public Page<Invoice> getAllInvoices(Pageable pageable) {
        return invoiceRepository.findAll(pageable);
    }

    public Invoice getInvoiceByNumber(long number) throws NotFoundException {
        return invoiceRepository.findByNumber(number).orElseThrow(()->new NotFoundException("Invoice with number= " + number + " was not found"));
    }
    public Invoice saveInvoice(InvoiceRequest invoiceRequest) throws NotFoundException {
        Invoice invoice = new Invoice();
        invoice.setCustomer(customerService.getCustomerById(invoiceRequest.getCustomerId()));
        invoice.setDate(invoiceRequest.getDate());
        invoice.setStatus(invoiceRequest.getStatus());

        return invoiceRepository.save(invoice);
    }

    public Invoice updateInvoice(long number, InvoiceRequest invoiceRequest) throws NotFoundException {
        Invoice invoice = getInvoiceByNumber(number);
        invoice.setCustomer(customerService.getCustomerById(invoiceRequest.getCustomerId()));
        invoice.setDate(invoiceRequest.getDate());
        invoice.setStatus(invoiceRequest.getStatus());

        return invoiceRepository.save(invoice);
    }

    public void deleteInvoice(long number) throws NotFoundException {
        Invoice x = getInvoiceByNumber(number);
        invoiceRepository.delete(x);
    }


    public Page<Invoice> getByCustomer(Customer customer, Pageable pageable) {
        return invoiceRepository.findByCustomer(customer, pageable);
    }

    public Page<Invoice> getByStatus(String status, Pageable pageable) {
        return invoiceRepository.findByStatus(status, pageable);
    }

    public Page<Invoice> getByDate(LocalDate date, Pageable pageable) {
        return invoiceRepository.findByDate(date, pageable);
    }

    // Metodo per recuperare le fatture emesse per un anno specifico
    public Page<Invoice> findByAnno(int year, Pageable pageable) {
        return invoiceRepository.findByAnno(year, pageable);
    }

    public Page<Invoice> getByDateBetween(LocalDate startDate, LocalDate endDate, Pageable pageable) {
        return invoiceRepository.findByDateBetween(startDate, endDate, pageable);
    }

    public Page<Invoice> getByImportoBetween(double minAmount, double maxAmount, Pageable pageable) {
        return invoiceRepository.findByImportoBetween(minAmount, maxAmount, pageable);
    }
}
