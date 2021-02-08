package com.shopping.service;

import com.shopping.models.Addresss;
import com.shopping.models.Basket;
import com.shopping.models.Customer;
import com.shopping.models.Order;
import com.shopping.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order save(Order order){
        return orderRepository.save(order);
    }

    public Order setOrderProperties(Customer customer, Basket basket, Addresss addresss){
        Order order = new Order();

        order.setCustomer(customer);
        order.setBasket(basket);
        order.setAddresss(addresss);

        return order;
    }

    public List<Order> findAllByCustomerId(Long customerId){
        return orderRepository.findAllByCustomerId(customerId);
    }

}
