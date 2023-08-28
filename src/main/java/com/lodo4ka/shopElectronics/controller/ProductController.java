package com.lodo4ka.shopElectronics.controller;

import com.lodo4ka.shopElectronics.persistance.model.Product;
import com.lodo4ka.shopElectronics.persistance.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    public ProductController() {
    }

    @GetMapping("/get/all")
    List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping("/add")
    void addProduct(@RequestBody Product product) {
        productService.addProduct(product);
    }
}
