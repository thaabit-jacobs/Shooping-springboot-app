package com.shopping.models;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


public class BasketPriceCalculator implements PriceCalculator{

    private Basket basket;

    public BasketPriceCalculator(Basket basket){
        this.basket = basket;
    }


    @Override
    public double calculateTotalPrice() {
        double total = 0.0;

        Set<Product> basketItems = new HashSet<>(basket.getProducts());

        for (Product product: basketItems){
            total += product.getPrice();
        }

        return total;
    }
}
