package com.example.Epic.Energy.Services.services;

import com.example.Epic.Energy.Services.entities.Customer;
import com.example.Epic.Energy.Services.exceptions.NotFoundException;
import com.example.Epic.Energy.Services.repositories.CustomerRepository;
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

    public Page<Customer> getAllCustomer(Pageable pageable){

        return customerRepository.findAll(pageable);
    }

    public Customer getCustomerById(long id) throws NotFoundException {
        return customerRepository.findById(id).orElseThrow(()->new NotFoundException("Customer with id= " + id + " was not found"));
    }


    public Customer saveCustomer(CustomerRequest customerRequest){
        Customer x = new Customer();
        x.setBusinessName(customerRequest.getBusinessName());
        x.setVatNumber(customerRequest.getVatNumber());
        x.setInsertionDate(LocalDate.now());
        x.setPec(customerRequest.getPec());
        x.setPhoneNumber(customerRequest.getPhoneNumber());
        x.setRegisteredOfficeAddress(customerRequest.getRegisteredOfficeAddress());
        x.setOperationalHeadquartersAddress(customerRequest.getOperationalHeadquartersAddress());
        x.setContactName(customerRequest.getContactName());
        x.setContactSurname(customerRequest.getContactSurname());
        x.setContactNumber(customerRequest.getContactNumber());
        x.setCustomerType(customerRequest.getCustomerType());
        x.setEmail(customerRequest.getEmail());

        return customerRepository.save(x);
    }

    public Customer updateCustomer(long id, CustomerRequest customerRequest) throws NotFoundException{
        Customer x = getCustomerById(id);
        x.setBusinessName(customerRequest.getBusinessName());
        x.setVatNumber(customerRequest.getVatNumber());
        x.setInsertionDate(LocalDate.now());
        x.setPec(customerRequest.getPec());
        x.setPhoneNumber(customerRequest.getPhoneNumber());
        x.setRegisteredOfficeAddress(customerRequest.getRegisteredOfficeAddress());
        x.setOperationalHeadquartersAddress(customerRequest.getOperationalHeadquartersAddress());
        x.setContactName(customerRequest.getContactName());
        x.setContactSurname(customerRequest.getContactSurname());
        x.setContactNumber(customerRequest.getContactNumber());
        x.setCustomerType(customerRequest.getCustomerType());
        x.setEmail(customerRequest.getEmail());

        return customerRepository.save(x);
    }

    public void deleteCustomer(long id) throws NotFoundException{
        Customer x = getCustomerById(id);
        customerRepository.delete(x);
    }

    public Customer udloadLogo(long id, String url) throws NotFoundException{
        Customer x = getCustomerById(id);
        x.setLogo(url);
        return customerRepository.save(x);
    }
}
