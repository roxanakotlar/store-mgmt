package com.example.demo2.service;

import com.example.demo2.model.Product;
import com.example.demo2.web.exception.NotFoundException;
import com.example.demo2.web.exception.ParameterException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Log4j2
@Component
public class StoreMgmtServiceImpl implements StoreMgmtService {

    @Autowired
    private StoreMgmtRepo storeRepo;

    public long addProduct(Product product) {
        Product p = storeRepo.save(product);
        return p.getId();
    }

    public void deleteProduct(long id) {
        storeRepo.findById(id)
                .orElseThrow(NotFoundException::new);
        storeRepo.deleteById(id);
    }


    /**
     * Update the Product with new values from req, no additional checks per field: if present or != null
     * @param product
     * @param id
     * @return updated product id
     * */
    @Override
    public long updateProduct( Product product, long id) {
        if (product.getId() != id) {
            throw new ParameterException();
        }
        storeRepo.findById(id)
                .orElseThrow(NotFoundException::new);
        storeRepo.save(product);

        return product.getId();
    }

    @Override
    public long updatePrice(Product product, long id) {
        if (product.getId() != id) {
            throw new ParameterException();
        }
        Product p = storeRepo.findById(product.getId()).orElseThrow(NotFoundException::new);
        if (p.getPrice() != product.getPrice()) {
            p.setPrice(product.getPrice());
            storeRepo.save(p);
        }
        else{
            log.warn("Same price value; price was not updated");
        }
        return product.getId();
    }

    @Override
    public Iterable<Product> productList() {
        return storeRepo.findAll();
    }

    @Override
    public Product findById(long id) {
        return storeRepo.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public Iterable<Product> findByName(String name) {
        return storeRepo.findByName(name);
    }

    @Override
    public Iterable<Product> findOutOfStock() {
        return storeRepo.findByQuantity(0);
    }
}
