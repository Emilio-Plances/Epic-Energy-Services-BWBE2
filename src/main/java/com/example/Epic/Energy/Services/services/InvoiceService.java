package com.example.Epic.Energy.Services.services;

import com.example.Epic.Energy.Services.entities.Invoice;
import com.example.Epic.Energy.Services.exceptions.NotFoundException;
import com.example.Epic.Energy.Services.repositories.InvoiceRepository;
import com.example.Epic.Energy.Services.requests.InvoiceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;

    public Page<Invoice> getAllInvoices(Pageable pageable) {
        return invoiceRepository.findAll(pageable);
    }

    public Invoice getInvoiceByNumber(String number) throws NotFoundException {
        return invoiceRepository.findByNumber(number).orElseThrow(()->new NotFoundException("Invoice with number= " + number + " was not found"));
    }
    public Invoice saveInvoice(InvoiceRequest invoiceRequest) {
        Invoice invoice = new Invoice();
        invoice.setCustomer(invoiceRequest.getCustomer());
        invoice.setDate(invoiceRequest.getDate());
        invoice.setStatus(invoiceRequest.getStatus());

        return invoiceRepository.save(invoice);
    }

    public Invoice updateInvoice(String number, InvoiceRequest invoiceRequest) throws NotFoundException {
        Invoice invoice = getInvoiceByNumber(number);
        invoice.setCustomer(invoiceRequest.getCustomer());
        invoice.setDate(invoiceRequest.getDate());
        invoice.setStatus(invoiceRequest.getStatus());

        return invoiceRepository.save(invoice);
    }

    public void deleteInvoice(String number) throws NotFoundException {
        Invoice x = getInvoiceByNumber(number);
        invoiceRepository.delete(x);
    }

}
