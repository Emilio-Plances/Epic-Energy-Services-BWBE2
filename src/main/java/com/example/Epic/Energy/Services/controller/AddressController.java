package com.example.Epic.Energy.Services.controller;

import com.example.Epic.Energy.Services.entities.Address;
import com.example.Epic.Energy.Services.exceptions.NotFoundException;
import com.example.Epic.Energy.Services.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
public class AddressController {
    @Autowired
    private AddressService addressService;
    @GetMapping
    public ResponseEntity<List<Address>> getAllAddresses() {
        List<Address> addresses = addressService.getAllAddresses();
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable Long id) throws NotFoundException {
        Address address = addressService.getAddressById(id);
        return new ResponseEntity<>(address, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Address> createAddress(@RequestBody Address address) {
        Address newAddress = addressService.createAddress(address);
        return new ResponseEntity<>(newAddress, HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable Long id, @RequestBody Address addressDetails) throws NotFoundException {
        Address address = addressService.updateAddress(id, addressDetails);
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAddress(@PathVariable Long id) throws NotFoundException {
        addressService.deleteAddress(id);
        String message = "User with ID" + id + " has been deleted";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }


}
