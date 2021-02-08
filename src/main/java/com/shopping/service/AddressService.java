package com.shopping.service;

import com.shopping.models.Addresss;
import com.shopping.models.Customer;
import com.shopping.repository.AddresssRepository;
import com.shopping.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddresssRepository addresssRepository;

    @Autowired
    private OrderRepository orderRepository;

    public List<Addresss> findAllByCustomerId(Customer customer){
        return addresssRepository.findAllByCustomerId(customer.getId());
    }

    public Addresss save(Addresss addresss){
        return addresssRepository.save(addresss);
    }

    public Addresss findById(Long selectedAddress){
        Optional<Addresss> selectedAddresssOptional = addresssRepository.findById(selectedAddress);
        Addresss addresss = null;

        if (selectedAddresssOptional.isPresent()){
            addresss = selectedAddresssOptional.get();
        }

        return addresss;
    }

    @Transactional
    public void deleteById(Long addressId){
        orderRepository.deleteAllByAddressesId(addressId);
        addresssRepository.deleteById(addressId);
    }
}
