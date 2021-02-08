package com.shopping.models;

import javax.persistence.*;
import java.util.Date;

@Table
@Entity(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinTable(name = "customer_orders")
    private Customer customer;

    @ManyToOne
    @JoinTable(name = "address_orders")
    private Addresss addresss;

    @ManyToOne
    @JoinTable(name = "basket_orders")
    private Basket basket;

    @Column(name = "placedAt")
    private Date placedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Addresss getAddresss() {
        return addresss;
    }

    public void setAddresss(Addresss addresss) {
        this.addresss = addresss;
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public Date getPlacedAt() {
        return placedAt;
    }

    public void setPlacedAt(Date placedAt) {
        this.placedAt = placedAt;
    }

    @PrePersist
    void createdAt(){
        this.placedAt = new Date();
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer=" + customer +
                ", addresss=" + addresss +
                ", basket=" + basket +
                ", placedAt=" + placedAt +
                '}';
    }
}
