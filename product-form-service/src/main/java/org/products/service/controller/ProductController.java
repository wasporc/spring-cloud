package org.products.service.controller;

import org.products.service.ProductService;
import org.products.service.dto.ProductDto;
import org.products.service.dto.ProductsCreationDto;
import org.products.service.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public String getAllProduct2(Model model){
        model.addAttribute("products", productService.findAll());
        return "allProduct";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Model model){
        productService.remove(id);
        model.addAttribute("products", productService.findAll());
        return "redirect:/";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        ProductsCreationDto productForm = new ProductsCreationDto();
        //for (int i = 1; i <= 3; i++) {
        productForm.addProduct(new ProductDto());
        // }
        model.addAttribute("form", productForm);
        return "createProductForm";
    }

    @PostMapping("/save")
    public String saveProduct(@ModelAttribute ProductsCreationDto form, Model model) {
        productService.saveAll(form);
        model.addAttribute("products", productService.findAll());
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable Long id, Model model){
        List<ProductDto> list = new ArrayList<>();
        list.add(mapping(productService.getById(id).orElse(new Product())));
        model.addAttribute("form", new ProductsCreationDto(list));
        return "editProductForm";
    }

    private static ProductDto mapping(Product product){
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        return dto;
    }

}
