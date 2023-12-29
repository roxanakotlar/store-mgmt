package com.example.demo2.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;
import java.util.Objects;

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

    private Date expiryDate;

    public Product() {
        super();
    }

    public Product(String name, Date expiryDate) {
        super();
        this.name = name;
        this.expiryDate = expiryDate;
    }

    public Product(String name, long price) {
        super();
        this.name = name;
        this.price = price;
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
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        return Objects.equals(id, ((Product) obj).id);
    }

    @Override
    public String toString() {
        return " id: " + id +
                ",\n name: " + name +
                ",\n brand: " + brand +
                ",\n description: " + description +
                ",\n price: " + price +
                ",\n expiryDate: " + expiryDate +
                ",\n quantity" + quantity +
                "\n";
    }
}
