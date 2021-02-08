package com.shopping.controller;

import com.shopping.models.Customer;
import com.shopping.repository.CustomerRepository;
import com.shopping.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
@SessionAttributes("customer")
public class LoginController {
    @Autowired
    private CustomerService customerService;

    @GetMapping
    public String loginUser(Model model){
        model.addAttribute("customer", new Customer());

        return "loginForm";
    }

    @PostMapping
    public String checkUserCredentials(@Valid Customer customer, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "loginForm";
        }

        customerService.saveCustomerIfNotExist(customer);

        return "redirect:/home";
    }

    @GetMapping("logout")
    public String logUserOut(SessionStatus sessionStatus){
        sessionStatus.setComplete();

        return "redirect:/";
    }
}
