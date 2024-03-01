//package com.example.Epic.Energy.Services;
//
//import com.example.Epic.Energy.Services.entities.Customer;
//import com.example.Epic.Energy.Services.services.CustomerService;
//import com.sun.tools.javac.Main;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.stereotype.Component;
//
//@Component
//public class Runner2 implements CommandLineRunner {
//    @Autowired
//    private CustomerService customerService;
//
//    public static void main(String[] args) {
//        SpringApplication.run(Runner2.class, args);
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        // Testa tutti i metodi del servizio Customer
//        testGetAllCustomers();
//
//    }
//
//
//    @Transactional
//    public void testGetAllCustomers() {
//        System.out.println("Test getAllCustomers:");
//        Page<Customer> customers = customerService.getAllCustomers(PageRequest.of(0, 10));
//        customers.forEach(System.out::println);
//    }
//
//
//
//}
