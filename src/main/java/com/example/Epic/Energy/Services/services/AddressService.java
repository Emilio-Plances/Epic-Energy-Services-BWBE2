package com.example.Epic.Energy.Services.services;

import com.example.Epic.Energy.Services.entities.Address;
import com.example.Epic.Energy.Services.exceptions.NotFoundException;
import com.example.Epic.Energy.Services.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    public Address getAddressById(Long id) throws NotFoundException {
        return addressRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Address not found"));
    }
    public Address createAddress(Address address) {
        return addressRepository.save(address);
    }
    public void deleteAddress(Long id) throws NotFoundException {
        if (!addressRepository.existsById(id)) {
            throw new NotFoundException("Address not found");
        }
        addressRepository.deleteById(id);
    }
}
