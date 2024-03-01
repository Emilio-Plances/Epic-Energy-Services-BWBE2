package com.example.Epic.Energy.Services.controller;

import com.cloudinary.Cloudinary;
import com.example.Epic.Energy.Services.entities.Customer;
import com.example.Epic.Energy.Services.exceptions.BadRequestExceptionHandler;
import com.example.Epic.Energy.Services.exceptions.NotFoundException;
import com.example.Epic.Energy.Services.requests.CustomerRequest;
import com.example.Epic.Energy.Services.responses.DefaultResponse;
import com.example.Epic.Energy.Services.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private Cloudinary cloudinary;

    @GetMapping("")
    public ResponseEntity<DefaultResponse> getAll(Pageable pageable){
        return DefaultResponse.noMessage(customerService.getAllCustomer(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DefaultResponse> getCustomerById(@PathVariable long id) throws NotFoundException {
        return DefaultResponse.noMessage(customerService.getCustomerById(id), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<DefaultResponse> saveCustomer(@RequestBody @Validated CustomerRequest customerRequest, BindingResult bindingResult) throws BadRequestExceptionHandler, NotFoundException {
        if (bindingResult.hasErrors()) throw new BadRequestExceptionHandler(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());
        return DefaultResponse.noMessage(customerService.saveCustomer(customerRequest), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DefaultResponse> updateCustomer(@PathVariable long id, @RequestBody @Validated CustomerRequest customerRequest, BindingResult bindingResult) throws BadRequestExceptionHandler, NotFoundException {
        if (bindingResult.hasErrors()) throw new BadRequestExceptionHandler(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());
        return DefaultResponse.noMessage(customerService.updateCustomer(id, customerRequest), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DefaultResponse> deleteCustomer(@PathVariable long id) throws NotFoundException {
        customerService.deleteCustomer(id);
        return DefaultResponse.noObject("Customer with id " + id + " has been deleted", HttpStatus.OK);
    }

    @PatchMapping("/{id}/upload")
    public ResponseEntity<DefaultResponse> uploadLogo(@PathVariable long id, @RequestParam("upload")MultipartFile file) throws IOException, NotFoundException {
        Customer x = customerService.udloadLogo(id, (String)cloudinary.uploader().upload(file.getBytes(), new HashMap()).get("url"));
        return DefaultResponse.full("Logo was uploaded successfully", x , HttpStatus.OK);
    }

    @GetMapping("/orderByNameAsc")
    public ResponseEntity<DefaultResponse> getCustomersOrderedByNameAsc(Pageable pageable) {
        return DefaultResponse.noMessage(customerService.customersOrderByRagioneSociale(pageable), HttpStatus.OK);
    }

    @GetMapping("/orderByAnnualTurnoverDesc")
    public ResponseEntity<DefaultResponse> getCustomersOrderedByAnnualTurnoverDesc(Pageable pageable) {
        return DefaultResponse.noMessage(customerService.customersOrderByFattAnnuale(pageable), HttpStatus.OK);
    }

    @GetMapping("/orderByInsertionDateDesc")
    public ResponseEntity<DefaultResponse> getCustomersOrderedByInsertionDateDesc(Pageable pageable) {
        return DefaultResponse.noMessage(customerService.customersOrderByDataInserimento(pageable), HttpStatus.OK);
    }

    @GetMapping("/orderByLastContactDateDesc")
    public ResponseEntity<DefaultResponse> getCustomersOrderedByLastContactDateDesc(Pageable pageable) {
        return DefaultResponse.noMessage(customerService.customersOrderByDataUltimoContatto(pageable), HttpStatus.OK);
    }

    @GetMapping("/orderByRegisteredOfficeProvinceAsc")
    public ResponseEntity<DefaultResponse> getCustomersOrderedByRegisteredOfficeProvinceAsc(Pageable pageable) {
        return DefaultResponse.noMessage(customerService.customersOrderByProvincia(pageable), HttpStatus.OK);
    }

    @GetMapping("/byAnnualTurnoverRange")
    public ResponseEntity<DefaultResponse> getCustomersByAnnualTurnoverRange(@RequestParam double minRevenue, @RequestParam double maxRevenue, Pageable pageable) {
        return DefaultResponse.noMessage(customerService.customersByIntervalloFatturatoAnnuale(minRevenue, maxRevenue, pageable), HttpStatus.OK);
    }

    @GetMapping("/byInsertionDateRange")
    public ResponseEntity<DefaultResponse> getCustomersByInsertionDateRange(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate, Pageable pageable) {
        return DefaultResponse.noMessage(customerService.customersByIntervalloDataInserimento(fromDate, toDate, pageable), HttpStatus.OK);
    }

    @GetMapping("/byLastContactDateRange")
    public ResponseEntity<DefaultResponse> getCustomersByLastContactDateRange(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate, Pageable pageable) {
        return DefaultResponse.noMessage(customerService.customersByIntervalloDataUltimoContatto(fromDate, toDate, pageable), HttpStatus.OK);
    }

    @GetMapping("/byNameContaining")
    public ResponseEntity<DefaultResponse> getCustomersByNameContaining(@RequestParam String name, Pageable pageable) {
        return DefaultResponse.noMessage(customerService.customersByRagioneSociale(name, pageable), HttpStatus.OK);
    }




}
