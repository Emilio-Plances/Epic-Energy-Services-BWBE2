package com.example.Epic.Energy.Services.services;

import com.example.Epic.Energy.Services.entities.Address;
import com.example.Epic.Energy.Services.entities.Customer;
import com.example.Epic.Energy.Services.entities.Municipality;
import com.example.Epic.Energy.Services.exceptions.NotFoundException;
import com.example.Epic.Energy.Services.repositories.AddressRepository;
import com.example.Epic.Energy.Services.repositories.CustomerRepository;
import com.example.Epic.Energy.Services.requests.AddressRequest;
import com.example.Epic.Energy.Services.requests.CustomerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private MunicipalityService municipalityService;

    public Page<Customer> getAllCustomer(Pageable pageable) {

        return customerRepository.findAll(pageable);
    }

    public Customer getCustomerById(long id) throws NotFoundException {
        return customerRepository.findById(id).orElseThrow(() -> new NotFoundException("Customer with id= " + id + " was not found"));
    }

    public Customer saveCustomer(CustomerRequest customerRequest) throws NotFoundException {
        Customer x = new Customer();
        updateCustomerDetails(x, customerRequest);
        return customerRepository.save(x);
    }

    public Customer updateCustomer(long id, CustomerRequest customerRequest) throws NotFoundException {
        Customer x = getCustomerById(id);
        updateCustomerDetails(x, customerRequest);
        return customerRepository.save(x);
    }

    private void updateCustomerDetails(Customer customer, CustomerRequest customerRequest) throws NotFoundException {
        customer.setBusinessName(customerRequest.getBusinessName());
        customer.setVatNumber(customerRequest.getVatNumber());
        customer.setInsertionDate(LocalDate.now());
        customer.setPec(customerRequest.getPec());
        customer.setPhoneNumber(customerRequest.getPhoneNumber());
        customer.setRegisteredOfficeAddress(getOrCreateAddress(customerRequest.getRegisteredOfficeAddressId(), customerRequest.getRegisteredOfficeAddress()));
        customer.setOperationalHeadquartersAddress(getOrCreateAddress(customerRequest.getOperationalHeadquartersAddressId(), customerRequest.getOperationalHeadquartersAddress()));
        customer.setContactName(customerRequest.getContactName());
        customer.setContactSurname(customerRequest.getContactSurname());
        customer.setContactNumber(customerRequest.getContactNumber());
        customer.setCustomerType(customerRequest.getCustomerType());
        customer.setEmail(customerRequest.getEmail());
    }

    private Address getOrCreateAddress(Long addressId, AddressRequest addressRequest) throws NotFoundException {
        if (addressId != null) {
            return addressRepository.findById(addressId)
                    .orElseThrow(() -> new NotFoundException("Address not found with ID: " + addressId));
        } else {
            Address newAddress = new Address();
            updateAddressDetails(newAddress, addressRequest);
            return addressRepository.save(newAddress);
        }
    }

    private void updateAddressDetails(Address address, AddressRequest addressRequest) throws NotFoundException{
        address.setStreet(addressRequest.getStreet());
        address.setStreetNumber(addressRequest.getStreetNumber());
        address.setCity(addressRequest.getCity());
        address.setPostalCode(addressRequest.getPostalCode());
        address.setCountry(addressRequest.getCountry());
        address.setProvince(addressRequest.getProvince());
        String cityName = addressRequest.getCity().substring(0, 1).toUpperCase() + addressRequest.getCity().substring(1).toLowerCase();

        Municipality municipality = municipalityService.findByName(cityName);
        if (municipality == null) {
            throw new NotFoundException("Municipality not found for city: " + cityName);
        }
        address.setMunicipality(municipality);
    }

    public void deleteCustomer(long id) throws NotFoundException {
        Customer x = getCustomerById(id);
        customerRepository.delete(x);
    }

    public Customer udloadLogo(long id, String url) throws NotFoundException {
        Customer x = getCustomerById(id);
        x.setLogo(url);
        return customerRepository.save(x);
    }


    public Page<Customer> getAllCustomers(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    public Page<Customer> customersOrderByRagioneSociale(Pageable pageable) {
        return customerRepository.findBusinessNameASC(pageable);
    }

    public Page<Customer> customersOrderByFattAnnuale(Pageable pageable) {
        return customerRepository.findAllOrderByFatturatoAnnualeDesc(pageable);
    }

    public Page<Customer> customersOrderByDataInserimento(Pageable pageable) {
        return customerRepository.findAllOrderByDataInserimentoDesc(pageable);
    }

    public Page<Customer> customersOrderByDataUltimoContatto(Pageable pageable) {
        return customerRepository.findAllOrderByDataUltimoContattoDesc(pageable);
    }

    public Page<Customer> customersOrderByProvincia(Pageable pageable) {
        return customerRepository.findAllOrderByProvinciaSedeLegaleAsc(pageable);
    }

    public Page<Customer> customersByIntervalloFatturatoAnnuale(double minRevenue, double maxRevenue, Pageable pageable) {
        return customerRepository.findByFatturatoAnnualeBetween(minRevenue, maxRevenue, pageable);
    }

    public Page<Customer> customersByIntervalloDataInserimento(LocalDate fromDate, LocalDate toDate, Pageable pageable) {
        return customerRepository.findByDataInserimentoBetween(fromDate, toDate, pageable);
    }

    public Page<Customer> customersByIntervalloDataUltimoContatto(LocalDate fromDate, LocalDate toDate, Pageable pageable) {
        return customerRepository.findByDataUltimoContattoBetween(fromDate, toDate, pageable);
    }

    public Page<Customer> customersByRagioneSociale(String name, Pageable pageable) {
        return customerRepository.findByRagioneSocialeContaining(name, pageable);
    }
}