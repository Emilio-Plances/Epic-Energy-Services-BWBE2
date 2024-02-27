package com.example.Epic.Energy.Services.controller;

import com.example.Epic.Energy.Services.exceptions.BadRequestExceptionHandler;
import com.example.Epic.Energy.Services.exceptions.NotFoundException;
import com.example.Epic.Energy.Services.requests.AddressRequest;
import com.example.Epic.Energy.Services.responses.DefaultResponse;
import com.example.Epic.Energy.Services.services.AddressService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/addresses")
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
    public ResponseEntity<DefaultResponse> createAddress(@RequestBody @Validated AddressRequest addressRequest, BindingResult bindingResult) throws NotFoundException, BadRequestExceptionHandler {
        if(bindingResult.hasErrors())
            throw new BadRequestExceptionHandler(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());
        return DefaultResponse.noMessage(addressService.createAddress(addressRequest), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<DefaultResponse> updateAddress(@PathVariable Long id, @RequestBody @Validated AddressRequest addressRequest,BindingResult bindingResult) throws NotFoundException, BadRequestExceptionHandler {
        if(bindingResult.hasErrors())
            throw new BadRequestExceptionHandler(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());
        return DefaultResponse.noMessage(addressService.updateAddress(id, addressRequest), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DefaultResponse> deleteAddress(@PathVariable Long id) throws NotFoundException {
        addressService.deleteAddress(id);
        String message = "User with ID" + id + " has been deleted";
        return DefaultResponse.noObject(message, HttpStatus.OK);
    }
}
