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

    public Customer getUserById(int id) throws NotFoundException {
        return customerRepository.findById(id).orElseThrow(()->new NotFoundException("Customer by id= " + id + " not found"));
    }


    public Customer saveCustomer(CustomerRequest customerRequest){
        Customer x = new Customer();
        x.setBusinessName(customerRequest.getBusinessName());
        x.setVatNumber(customerRequest.getVatNumber());
        x.setInsertionDate(LocalDate.now());

        return customerRepository.save(x);
    }
}
