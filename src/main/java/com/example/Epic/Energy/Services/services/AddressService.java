package com.example.Epic.Energy.Services.services;

import com.example.Epic.Energy.Services.entities.Address;
import com.example.Epic.Energy.Services.exceptions.NotFoundException;
import com.example.Epic.Energy.Services.repositories.AddressRepository;
import com.example.Epic.Energy.Services.requests.AddressRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class AddressService {
    @Autowired
    private MunicipalityService municipalityService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private AddressRepository addressRepository;

    public Page<Address> getAllAddresses(Pageable pageable) {
        return addressRepository.findAll(pageable);
    }

    public Address getAddressById(Long id) throws NotFoundException {
        return addressRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Address not found"));
    }
    public Address createAddress(AddressRequest addressRequest) throws NotFoundException {
        Address address=new Address();
        address.setStreet(address.getStreet());
        address.setStreetNumber(addressRequest.getStreetNumber());
        address.setCity(addressRequest.getCity());
        address.setCountry(addressRequest.getCountry());
        address.setCustomer(customerService.getCustomerById(addressRequest.getCustomerId()));
        String municipality=addressRequest.getMunicipality().toUpperCase().charAt(0)+addressRequest.getMunicipality().substring(1).toUpperCase();
        address.setMunicipality(municipalityService.findByName(municipality));
        return addressRepository.save(address);
    }
    public void deleteAddress(Long id) throws NotFoundException {
        Address address=getAddressById(id);
        addressRepository.delete(address);
    }
    public Address updateAddress(Long id, AddressRequest addressRequest) throws NotFoundException {
        Address address = getAddressById(id);
        address.setStreet(address.getStreet());
        address.setStreetNumber(addressRequest.getStreetNumber());
        address.setCity(addressRequest.getCity());
        address.setCountry(addressRequest.getCountry());
        address.setCustomer(customerService.getCustomerById(addressRequest.getCustomerId()));
        String municipality=addressRequest.getMunicipality().toUpperCase().charAt(0)+addressRequest.getMunicipality().substring(1).toUpperCase();
        address.setMunicipality(municipalityService.findByName(municipality));
        return addressRepository.save(address);
    }
}
