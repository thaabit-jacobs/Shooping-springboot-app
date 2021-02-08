package com.shopping.repository;

import com.shopping.models.Basket;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Long> {

    List<Basket> findAllByCustomerId(Long id);

    @Modifying
    @Query(value = "delete from basket_products where basket_id=:basketId and product_id=:productId",
    nativeQuery = true)
    int deleteByBasketIdAndProductId(@Param("basketId") long basketId, @Param("productId") long productId);

    @Modifying
    @Query(value = "delete from basket_products where basket_id=:basketId",
            nativeQuery = true)
    int deleteBasketFromBasketProductsId(@Param("basketId") long basketId);

}
