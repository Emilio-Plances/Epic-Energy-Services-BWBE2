package com.example.Epic.Energy.Services.controller;

import com.example.Epic.Energy.Services.entities.Customer;
import com.example.Epic.Energy.Services.exceptions.BadRequestExceptionHandler;
import com.example.Epic.Energy.Services.exceptions.NotFoundException;
import com.example.Epic.Energy.Services.repositories.InvoiceRepository;
import com.example.Epic.Energy.Services.requests.InvoiceRequest;
import com.example.Epic.Energy.Services.responses.DefaultResponse;
import com.example.Epic.Energy.Services.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private InvoiceRepository invoiceRepository;
    @GetMapping
    public ResponseEntity<DefaultResponse>getAllInvoices(Pageable pageable){
        return DefaultResponse.noMessage(invoiceService.getAllInvoices(pageable), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<DefaultResponse> getInvoiceById(@PathVariable long number)throws NotFoundException {
        return DefaultResponse.noMessage(invoiceService.getInvoiceByNumber(number),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<DefaultResponse> createInvoice(@RequestBody @Validated InvoiceRequest invoiceRequest, BindingResult bindingResult) throws BadRequestExceptionHandler, NotFoundException {
        if (bindingResult.hasErrors()) throw new BadRequestExceptionHandler(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());
        return DefaultResponse.noMessage(invoiceService.saveInvoice(invoiceRequest), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<DefaultResponse> updateInvoice(@PathVariable long number, @RequestBody @Validated InvoiceRequest invoiceRequest, BindingResult bindingResult) throws BadRequestExceptionHandler, NotFoundException {
        if (bindingResult.hasErrors()) throw new BadRequestExceptionHandler(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());
        return DefaultResponse.noMessage(invoiceService.updateInvoice(number, invoiceRequest), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<DefaultResponse> deleteInvoice(@PathVariable long number) throws NotFoundException {
        invoiceService.deleteInvoice(number);
        return DefaultResponse.noObject("Invoice with number " + number + " has been deleted", HttpStatus.OK);
    }


    @GetMapping("/byCustomer")
    public ResponseEntity<DefaultResponse> getInvoicesByCustomer(@RequestParam long customerId, Pageable pageable) {
        return DefaultResponse.noMessage(invoiceService.getByCustomer(customerId, pageable), HttpStatus.OK);
    }



    @GetMapping("/byStatus")
    public ResponseEntity<DefaultResponse> getInvoicesByStatus(@RequestParam String status, Pageable pageable) {
        return DefaultResponse.noMessage(invoiceService.getByStatus(status, pageable), HttpStatus.OK);
    }

    @GetMapping("/byDate")
    public ResponseEntity<DefaultResponse> getInvoicesByDate(@RequestParam LocalDate date, Pageable pageable) {
        return DefaultResponse.noMessage(invoiceService.getByDate(date, pageable), HttpStatus.OK);
    }

    @GetMapping("/byYear")
    public ResponseEntity<DefaultResponse> getInvoicesByYear(@RequestParam int year, Pageable pageable) {
        return DefaultResponse.noMessage(invoiceService.findByAnno(year, pageable), HttpStatus.OK);
    }

    @GetMapping("/byDateBetween")
    public ResponseEntity<DefaultResponse> getInvoicesByDateBetween(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate, Pageable pageable) {
        return DefaultResponse.noMessage(invoiceService.getByDateBetween(startDate, endDate, pageable), HttpStatus.OK);
    }

    @GetMapping("/byImportoBetween")
    public ResponseEntity<DefaultResponse> getInvoicesByImportoBetween(@RequestParam double minAmount, @RequestParam double maxAmount, Pageable pageable) {
        return DefaultResponse.noMessage(invoiceService.getByImportoBetween(minAmount, maxAmount, pageable), HttpStatus.OK);
    }

}
