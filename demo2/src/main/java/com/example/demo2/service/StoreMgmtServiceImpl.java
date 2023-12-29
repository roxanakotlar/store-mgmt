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
        log.trace("add product " + product);
        Product p = storeRepo.save(product);
        return p.getId();
    }

    public void deleteProduct(long id) {
        log.trace("delete product " + id);
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
    public Product updateProduct(Product product, long id) {
        log.trace("update product " + product);

        if (product.getId() != id) {
            throw new ParameterException();
        }
        storeRepo.findById(id)
                .orElseThrow(NotFoundException::new);
        storeRepo.save(product);

        return product;
    }

    @Override
    public Product updatePrice(Product product, long id) {
        log.trace("update product price " + product);
        if (product.getId() != id) {
            throw new ParameterException();
        }
        Product p = storeRepo.findById(product.getId()).orElseThrow(NotFoundException::new);
        if (p.getPrice() != product.getPrice()) {
            p.setPrice(product.getPrice());
            storeRepo.save(p);
            return p;
        } else {
            log.warn("Same price value; price was not updated");
            return null;
        }
    }

    @Override
    public Iterable<Product> productList() {
        log.trace("get product");
        return storeRepo.findAll();
    }

    @Override
    public Product findById(long id) {
        log.trace("get product " + id);
        return storeRepo.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public Iterable<Product> findByName(String name) {
        log.trace("get product " + name);
        return storeRepo.findByName(name);
    }

    @Override
    public Iterable<Product> findOutOfStock() {
        log.trace("get out of stock product ");
        return storeRepo.findByQuantity(0);
    }
}
