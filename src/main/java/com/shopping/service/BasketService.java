package com.shopping.service;

import com.shopping.models.Basket;
import com.shopping.repository.BasketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BasketService {

    @Autowired
    private BasketRepository basketRepository;

    public List<Basket> findAllByCustomerId(Long customerId){
        return basketRepository.findAllByCustomerId(customerId);
    }

    public Basket save(Basket basket){
        return basketRepository.save(basket);
    }

    public void deleteById(Long basketId){
        basketRepository.deleteById(basketId);
    }

    public int deleteBasketFromBasketProductsId(Long basketId){
        return basketRepository.deleteBasketFromBasketProductsId(basketId);
    }

    public Basket findById(Long basketId){
        Optional<Basket> basketOptional = basketRepository.findById(basketId);

        Basket basket = null;

        if (basketOptional.isPresent()){
            basket = basketOptional.get();
        }

        return basket;
    }

    public int deleteByBasketIdAndProductId(Long basketId, Long productId){
        return basketRepository.deleteByBasketIdAndProductId(basketId, productId);
    }
}
