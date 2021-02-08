package com.shopping.controller;

import com.shopping.models.Basket;
import com.shopping.models.Customer;
import com.shopping.models.Order;
import com.shopping.repository.BasketRepository;
import com.shopping.repository.CustomerRepository;
import com.shopping.repository.OrderRepository;
import com.shopping.repository.ProductRepository;
import com.shopping.service.BasketService;
import com.shopping.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/home")
@SessionAttributes("customer")
public class HomeController {

    @Autowired
    private BasketService basketService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping
    public String showHome(Basket basket, Model model, @ModelAttribute Customer customer){
        customer = customerService.findCustomerByUserName(customer);

        List<Basket> basketList = basketService.findAllByCustomerId(customer.getId());

        List<Order> orderList = orderRepository.findAllByCustomerId(customer.getId());

        model.addAttribute("basket", new Basket());
        model.addAttribute("basketList", basketList);
        model.addAttribute("orderList", orderList);
        model.addAttribute("username", customer.getUsername());

        return "baskets";
    }

    @PostMapping
    public String addNewBasket(@Valid Basket basket, BindingResult bindingResult, @ModelAttribute Customer customer){
        if(bindingResult.hasErrors()){
            return "baskets";
        }

        customer = customerService.findCustomerByUserName(customer);

        basket.setCustomer(customer);
        basketService.save(basket);

        return "redirect:/home";
    }

    @Transactional
    @GetMapping("/delete/{basketId}")
    public String deleteBasket(@PathVariable Long basketId){
        basketService.deleteById(basketId);
        basketService.deleteBasketFromBasketProductsId(basketId);

        return "redirect:/home";
    }

}
