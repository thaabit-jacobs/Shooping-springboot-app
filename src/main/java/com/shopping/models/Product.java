package com.shopping.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;

    @Column(name = "barcode")
    private UUID barcode;

    @ManyToMany
            @JoinTable(
                    name = "basket_products",
                    joinColumns = @JoinColumn(name = "product_id"),
                    inverseJoinColumns = @JoinColumn(name = "basket_id")
            )
    Set<Basket> baskets = new HashSet<>();

    public Product(){}

    public Product(String name, Double price) {
        this.name = name;
        this.price = price;
        this.barcode = UUID.randomUUID();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public UUID getBarcode() {
        return barcode;
    }

    public void setBarcode(UUID barcode) {
        this.barcode = barcode;
    }

    public Set<Basket> getBaskets() {
        return baskets;
    }

    public void setBaskets(Set<Basket> baskets) {
        this.baskets = baskets;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Basket)) return false;

        Product product = (Product) o;
        return id.equals(product.id) && barcode.toString().equals(product.barcode);
    }

    @Override
    public int hashCode() {
        return this.getId().intValue();
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", barcode=" + barcode +
                '}';
    }
}
