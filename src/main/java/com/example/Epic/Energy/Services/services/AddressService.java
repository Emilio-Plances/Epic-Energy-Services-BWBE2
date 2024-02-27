package com.example.Epic.Energy.Services.services;

import com.example.Epic.Energy.Services.entities.Address;
import com.example.Epic.Energy.Services.repositories.AddressRepository;
import com.example.Epic.Energy.Services.requests.AddressRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable; // Import corretto
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Page<Address> getAll(Pageable pageable) {
        return addressRepository.findAll(pageable);
    }

    public Address getAddressById(Long id) {
        return addressRepository.findById(id).orElse(null);
    }

    public Address createAddress(AddressRequest addressRequest) {
        Address address = new Address();
        address.setStreet(addressRequest.getStreet());
        address.setStreetNumber(addressRequest.getStreetNumber());
        address.setCity(addressRequest.getCity());
        address.setPostalCode(addressRequest.getPostalCode());
        address.setCountry(addressRequest.getCountry());
        address.setMunicipality(addressRequest.getMunicipality());
        address.setCustomer(addressRequest.getCustomer());
        return addressRepository.save(address);
    }

    public Address updateAddress(Long id, AddressRequest addressRequest) {
        Address existingAddress = addressRepository.findById(id).orElse(null);
        if (existingAddress != null) {
            existingAddress.setStreet(addressRequest.getStreet());
            existingAddress.setStreetNumber(addressRequest.getStreetNumber());
            existingAddress.setCity(addressRequest.getCity());
            existingAddress.setPostalCode(addressRequest.getPostalCode());
            existingAddress.setCountry(addressRequest.getCountry());
            existingAddress.setMunicipality(addressRequest.getMunicipality());
            existingAddress.setCustomer(addressRequest.getCustomer());
            return addressRepository.save(existingAddress);
        }
        return null;
    }

    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }
}
