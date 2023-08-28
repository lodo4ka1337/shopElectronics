package com.lodo4ka.shopElectronics.controller;

import com.lodo4ka.shopElectronics.persistance.model.Product;
import com.lodo4ka.shopElectronics.persistance.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @GetMapping("/get/{id}")
    List<Product> getAllProductsOfShowcase(@PathVariable("id") UUID id) {
        return productService.getAllProductsOfShowcase(id);
    }

    @GetMapping("/get/type")
    List<Product> getAllProductsOfShowcaseFilteredByType(@RequestParam(value = "showcaseId") UUID id, @RequestParam(value = "type") String type) {
        return productService.getAllProductsOfShowcaseFilteredByType(id, type);
    }

    @GetMapping("/get/price")
    List<Product> getAllProductsOfShowcaseFilteredByPriceBetween(@RequestParam(value = "showcaseId") UUID id, @RequestParam(value = "price1") double price1, @RequestParam(value = "price2") double price2) {
        return productService.getAllProductsOfShowcaseWithPriceBetween(id, price1, price2);
    }

    @PostMapping("/add")
    void addProduct(@RequestBody Product product) {
        productService.addProduct(product);
    }

    @DeleteMapping("/delete/{id}")
    void deleteProduct(@PathVariable UUID id) {
        productService.deleteProduct(id);
    }

    @PutMapping("/update")
    void updateProduct(@RequestBody Product product) {
        productService.updateProduct(product);
    }
}
