package com.example.Epic.Energy.Services.controller;

import com.example.Epic.Energy.Services.exceptions.NotFoundException;
import com.example.Epic.Energy.Services.requests.AddressRequest;
import com.example.Epic.Energy.Services.responses.DefaultResponse;
import com.example.Epic.Energy.Services.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/services")
public class AddressController {
    @Autowired
    private AddressService addressService;
    @GetMapping
    public ResponseEntity<DefaultResponse> getAllAddresses(Pageable pageable) {
        return DefaultResponse.noMessage(addressService.getAllAddresses(pageable),HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<DefaultResponse> getAddressById(@PathVariable Long id) throws NotFoundException {
        return DefaultResponse.noMessage(addressService.getAddressById(id),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<DefaultResponse> createAddress(@RequestBody AddressRequest addressRequest) throws NotFoundException {
        return DefaultResponse.noMessage(addressService.createAddress(addressRequest), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<DefaultResponse> updateAddress(@PathVariable Long id, @RequestBody AddressRequest addressRequest) throws NotFoundException {
        return DefaultResponse.noMessage(addressService.updateAddress(id, addressRequest), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DefaultResponse> deleteAddress(@PathVariable Long id) throws NotFoundException {
        addressService.deleteAddress(id);
        String message = "User with ID" + id + " has been deleted";
        return DefaultResponse.noObject(message, HttpStatus.OK);
    }
}
