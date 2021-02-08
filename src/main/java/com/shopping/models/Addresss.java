package com.shopping.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "addresses")
public class Addresss {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    @Size(min = 6, message = "must at least be 6 characters long")
    @Column(name = "street")
    private String street;

    @NotBlank
    @NotNull
    @Size(min = 6, message = "must at least be 6 characters long")
    @Column(name = "city")
    private String city;

    @NotBlank
    @NotNull
    @Size(min = 6, message = "must at least be 6 characters long")
    @Column(name = "state")
    private String state;

    @NotBlank
    @NotNull
    @Size(min = 4, message = "must at least be 4 characters long")
    @Column(name = "zip")
    private String zip;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "addresss")
    private Set<Order> orders = new HashSet<>();

    public Addresss(){

    }

    public Addresss(String street, String city, String state, String zip) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return
                "" + street + "\n" +
                "," + city + "\n" +
                "," + state + "\n" +
                "," + zip + "\n";
    }
}
