package com.shopping.controller;

import com.shopping.models.Product;
import com.shopping.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public String showAdminView(Model model){
        model.addAttribute("productList", productService.findAll());
        model.addAttribute("product", new Product());

        return "adminView";
    }

    @PostMapping
    public String addProduct(@Valid Product product){
        product = new Product(product.getName(), product.getPrice());
        productService.save(product);

        return "redirect:/admin";
    }
}
