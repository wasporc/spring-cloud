package org.products.service.dto;

import java.util.ArrayList;
import java.util.List;

public class ProductsCreationDto {
    private List<ProductDto> products;

    public ProductsCreationDto(List<ProductDto> products) {
        this.products = products;
    }

    public ProductsCreationDto() {
        this(new ArrayList<>());
    }

    public void addProduct(ProductDto product) {
        this.products.add(product);
    }

    public List<ProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDto> products) {
        this.products = products;
    }
}
