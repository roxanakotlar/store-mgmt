package com.example.demo2.service;

import com.example.demo2.model.Product;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreMgmtRepo  extends CrudRepository<Product, String>{


}
