package com.example.demo2.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue
    @Column(nullable = false, unique = true)
    private long id;

    @Column(nullable = false)
    private String name;

    private String brand;

    private long price;

    private String description;

    private int quantity;

    @Column(nullable = false)
    private Date expiryDate;

    public Product() {
        super();
    }

    public Product(String name, Date expiryDate) {
        super();
        this.name = name;
        this.expiryDate = expiryDate;
    }

    public Product(String name, String brand, long price, String description, int quantity, Date expiryDate) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
    }

    @Override
    public boolean equals(Object obj) {
        //same name and brand, same id?
        return false;
    }

    @Override
    public String toString() {
        return name;
    }
}
