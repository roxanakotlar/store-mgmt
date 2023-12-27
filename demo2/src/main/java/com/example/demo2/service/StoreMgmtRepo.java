package com.example.demo2.service;

import com.example.demo2.model.Product;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreMgmtRepo  extends CrudRepository<Product, Long>{
  //  Product findById(long id);
    List<Product> findByName(String name);
    List<Product> findByQuantity(int quantity);


    void deleteById(long id);
}
