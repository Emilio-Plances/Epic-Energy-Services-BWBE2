package com.example.Epic.Energy.Services.controller;

import com.example.Epic.Energy.Services.entities.Address;
import com.example.Epic.Energy.Services.requests.AddressRequest;
import com.example.Epic.Energy.Services.responses.DefaultResponse;
import com.example.Epic.Energy.Services.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public ResponseEntity<DefaultResponse> getAllAddresses() {
        List<Address> addresses = addressService.getAllAddresses();
        return DefaultResponse.full("Lista di indirizzi recuperata con successo", addresses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DefaultResponse> getAddressById(@PathVariable Long id) {
        Address address = addressService.getAddressById(id);
        if (address != null) {
            return DefaultResponse.full("Indirizzo recuperato con successo", address, HttpStatus.OK);
        } else {
            return DefaultResponse.noObject("Indirizzo non trovato", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<DefaultResponse> createAddress(@RequestBody @Validated AddressRequest addressRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return DefaultResponse.noObject(bindingResult.getAllErrors().toString(), HttpStatus.BAD_REQUEST);
        }
        Address createdAddress = addressService.createAddress(addressRequest);
        return DefaultResponse.full("Indirizzo creato con successo", createdAddress, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DefaultResponse> updateAddress(@PathVariable Long id, @RequestBody @Validated AddressRequest addressRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return DefaultResponse.noObject(bindingResult.getAllErrors().toString(), HttpStatus.BAD_REQUEST);
        }
        Address updatedAddress = addressService.updateAddress(id, addressRequest);
        if (updatedAddress != null) {
            return DefaultResponse.full("Indirizzo aggiornato con successo", updatedAddress, HttpStatus.OK);
        } else {
            return DefaultResponse.noObject("Indirizzo non trovato", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DefaultResponse> deleteAddress(@PathVariable Long id) {
        addressService.deleteAddress(id);
        return DefaultResponse.noMessage(null, HttpStatus.NO_CONTENT);
    }
}