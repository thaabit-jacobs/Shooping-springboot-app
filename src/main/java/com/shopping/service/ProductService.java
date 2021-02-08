package com.shopping.service;

import com.shopping.models.Basket;
import com.shopping.models.Product;
import com.shopping.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Iterable<Product> findAllByBasketsId(Long basketId){
        return productRepository.findAllByBasketsId(basketId);
    }

    public List<Product> findAllById(List<Long> productIdList){
        return productRepository.findAllById(productIdList);
    }

    public Product save(Product product){
        return productRepository.save(product);
    }

    public void addProductsToBasket(List<Product> productList, Basket basket){
        for (Product product: productList){
            basket.getProducts().add(product);
            product.getBaskets().add(basket);

            this.save(product);
        }
    }
}
