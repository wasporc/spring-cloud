package org.products.service;

import org.products.service.dto.ProductsCreationDto;
import org.products.service.entity.Product;
import org.products.service.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Optional<Product> getById(Long id){
        return productRepository.findById(id);
    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Product add(Product product){
        return productRepository.save(product);
    }

    public void remove(Long id) throws RuntimeException{
        try {
            productRepository.deleteById(id);
        }catch (Exception e){
            throw new RuntimeException("can't delete");
        }
    }

    public void saveAll(ProductsCreationDto productsCreationDto){
        productsCreationDto.getProducts()
                .stream()
                .map(d -> {
                    Product p = new Product();
                    p.setName(d.getName());
                    p.setPrice(d.getPrice());
                    return p;
                })
                .forEach(this::add);
    }

}
