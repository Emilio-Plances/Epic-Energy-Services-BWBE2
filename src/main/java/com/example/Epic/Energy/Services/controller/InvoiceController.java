package com.example.Epic.Energy.Services.controller;

import com.example.Epic.Energy.Services.entities.Invoice;
import com.example.Epic.Energy.Services.exceptions.NotFoundException;
import com.example.Epic.Energy.Services.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;
    @GetMapping
    public ResponseEntity<List<Invoice>>getAllInvoices(){
        List<Invoice>invoices= invoiceService.getAllInvoices();
        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Invoice> getInvoiceById(@PathVariable Long id)throws NotFoundException {
        Invoice invoice=invoiceService.getInvoiceById(id);
        return new ResponseEntity<>(invoice, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Invoice> createInvoice(@RequestBody Invoice invoice) {
        Invoice createdInvoice = invoiceService.createInvoice(invoice);
        return new ResponseEntity<>(createdInvoice, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Invoice> updateInvoice(@PathVariable Long id, @RequestBody Invoice invoice) throws NotFoundException {
        Invoice updatedInvoice = invoiceService.updateInvoice(id, invoice);
        return new ResponseEntity<>(updatedInvoice, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable Long id) throws NotFoundException {
        invoiceService.deleteInvoice(id);
        String message = "Invoice with ID" + id + " has been deleted";
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
