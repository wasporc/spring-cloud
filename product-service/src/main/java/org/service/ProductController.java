package org.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    public List<Product> getAllProduct(){
        return service.findAll();
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        service.remove(id);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Product addProduct(@RequestBody Product product){
        product = service.add(product);
        return product;
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Product update(@RequestBody Product product){
        product = service.add(product);
        return product;
    }
}
