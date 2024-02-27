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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    public ResponseEntity<DefaultResponse> saveCustomer(@RequestBody @Validated CustomerRequest customerRequest, BindingResult bindingResult) throws BadRequestExceptionHandler {
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



}
