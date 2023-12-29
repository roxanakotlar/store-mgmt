package com.example.demo2.service;

import com.example.demo2.model.Product;
import org.springframework.web.bind.annotation.*;

public interface StoreMgmtService {

    long addProduct(@RequestBody Product product);


    void deleteProduct(@PathVariable long id);


    Product updateProduct(@RequestBody Product product,
                         @PathVariable long id);

    Product updatePrice(@RequestBody Product product, @PathVariable long id);


    Iterable<Product> productList();


    Product findById(@PathVariable long id);


    Iterable<Product> findByName(@PathVariable String name);

    Iterable<Product> findOutOfStock();
}
