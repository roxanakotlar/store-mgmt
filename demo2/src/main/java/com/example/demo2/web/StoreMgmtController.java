package com.example.demo2.web;
import com.example.demo2.model.Product;
import com.example.demo2.service.StoreMgmtService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/api/store-mgmt")
public class StoreMgmtController {

    @Autowired
    private StoreMgmtService storeMgmtService;


    @GetMapping("/hello")
    public String hello() {
        log.trace("calling hello");
        return "hello store!";
    }


    @PostMapping("/add-product")
    public long addProduct(@RequestBody Product product) {
       return storeMgmtService.addProduct(product);
    }


    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable long id) {
       storeMgmtService.deleteProduct(id);
    }


    /**
    * Update the Product with new values from req, no additional checks per field: if present or != null
     * @param product
     * @param id
     * @return updated product id
    * */

    @PutMapping("/{id}")
    public long updateProduct(@RequestBody Product product,
                                @PathVariable long id) {
        return storeMgmtService.updateProduct(product, id);
    }


    @PutMapping("/change-price/{id}")
    public long updatePrice(@RequestBody Product product, @PathVariable long id) {
      return storeMgmtService.updatePrice(product, id);
    }


    @GetMapping("/get-all-products")
    public Iterable<Product> productList() {
        return storeMgmtService.productList();
    }


    @GetMapping("/find-by-id/{id}")
    public Product findById(@PathVariable long id) {
        return storeMgmtService.findById(id);
    }


    @GetMapping("/find-by-name/{name}")
    public Iterable<Product> findByName(@PathVariable String name) {
        return storeMgmtService.findByName(name);
    }


    @GetMapping("/find-out-of-stock")
    public Iterable<Product> findOutOfStock() {
        return storeMgmtService.findOutOfStock();
    }
}
