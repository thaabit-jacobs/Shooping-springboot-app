package com.shopping.service;

import com.shopping.models.Customer;
import com.shopping.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public Customer findCustomerByUserName(Customer customer){
        return customerRepository.findByUsername(customer.getUsername());
    }

    public Customer saveCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    public Optional<Customer> saveCustomerIfNotExist(Customer customer){
        Customer savedCustomer = this.findCustomerByUserName(customer);

        if(savedCustomer == null){
            return Optional.of(this.saveCustomer(customer));
        }

        return Optional.empty();
    }
}
