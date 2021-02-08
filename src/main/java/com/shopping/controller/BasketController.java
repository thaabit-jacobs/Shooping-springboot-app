package com.shopping.controller;

import com.shopping.models.*;
import com.shopping.repository.*;
import com.shopping.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.transaction.annotation.Transactional;;
import javax.validation.Valid;
import java.util.*;


@RequestMapping("/basket")
@SessionAttributes("customer")
@Controller
@Slf4j
public class BasketController {
    private Long basketId;

    @Autowired
    private ProductService productService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private BasketService basketService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/{basketId}")
    public String showBasket(ProductsList productsList, Model model, @PathVariable Long basketId){
        List<Product> productList = productService.findAll();

        Iterable<Product> basketProductList = productService.findAllByBasketsId(basketId);

        this.basketId = basketId;

        Basket basket = basketService.findById(basketId);

        BasketPriceCalculator basketPriceCalculator = new BasketPriceCalculator(basket);

        model.addAttribute("selectProductList", new ProductsList());
        model.addAttribute("basketProductList", basketProductList);
        model.addAttribute("allProductList", productList);
        model.addAttribute("basketName", basket.getName());
        model.addAttribute("totalCost", basketPriceCalculator.calculateTotalPrice());

        return "basketProducts";
    }

    @PostMapping("/{basketId}")
    public String addProductToBasket(@Valid ProductsList productsList, @PathVariable Long basketId, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return  "redirect:/basket/" + basketId.toString();
        }

        List<Long> productIdList = productsList.getProductIds();

        List<Product> productList =  productService.findAllById(productIdList);

        Basket basket = basketService.findById(basketId);;

        productService.addProductsToBasket(productList, basket);

        return "redirect:/basket/" + basketId;
    }

    @Transactional
    @GetMapping("/delete/{productId}")
    public String deleteProductFromBasket(@PathVariable Long productId){
        Basket basket = basketService.findById(basketId);;

        basketService.deleteByBasketIdAndProductId(basketId, productId);

        return "redirect:/basket/" + basketId;
    }

    @GetMapping("/address")
    public String showAddresses(Model model, @ModelAttribute Customer customer){
        log.info(basketId.toString());

        customer = customerService.findCustomerByUserName(customer);

        List<Addresss> customersAddresses = addressService.findAllByCustomerId(customer);

        model.addAttribute("addressList", customersAddresses);
        model.addAttribute("address", new Addresss());

        return "addressForm";
    }

    @PostMapping("/add/address")
    public String addNewAddress(@Valid Addresss address, BindingResult bindingResult, @ModelAttribute Customer customer){
        log.info(basketId.toString());
        if (bindingResult.hasErrors()){
            return "redirect:/basket/address";
        }

        customer = customerService.findCustomerByUserName(customer);

        address.setCustomer(customer);

        addressService.save(address);

        return "redirect:/basket/address";
    }

    @GetMapping("/delete/address/{addressId}")
    public String deleteAddress(@PathVariable Long addressId){
        addressService.deleteById(addressId);

        return "redirect:/basket/address";
    }

    @PostMapping("/select/address")
    public String selectedCustomerAddress(@RequestParam(name = "selectedAddress") String selectedAddress, @ModelAttribute Customer customer, Model model){
        customer = customerService.findCustomerByUserName(customer);

        Basket basket1 = basketService.findById(basketId);;

        Addresss addresss = addressService.findById(Long.parseLong(selectedAddress));

        Order order = orderService.setOrderProperties(customer, basket1, addresss);

        Order order1 = orderService.save(order);

        model.addAttribute("username", customer.getUsername());
        model.addAttribute("street", addresss.getStreet());
        model.addAttribute("city", addresss.getCity());
        model.addAttribute("state", addresss.getState());
        model.addAttribute("zip", addresss.getZip());
        model.addAttribute("productList", basket1.getProducts());
        model.addAttribute("total", new BasketPriceCalculator(basket1).calculateTotalPrice());
        model.addAttribute("orderNumber", order1.getId());

        return "orderSuccess";
    }


}
