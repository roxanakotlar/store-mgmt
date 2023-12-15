package com.example.demo2.web;
import com.example.demo2.model.Product;
import com.example.demo2.service.StoreMgmtRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/store-mgmt")
public class StoreMgmtController {

    @Autowired
    private StoreMgmtRepo storeRepo;

    @GetMapping("/hello")
    public String hello() {
        return "hello store!";
    }

    @PostMapping("/add-product")
    public String addProduct(@RequestBody Product product) {
        Product p = storeRepo.save(product);
        return "added a product with id " + p.getId();
    }

    @GetMapping("/get-all-products")
    public Iterable<Product> productList() {
        return storeRepo.findAll();
    }
}
