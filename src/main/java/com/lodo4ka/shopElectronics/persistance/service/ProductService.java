package com.lodo4ka.shopElectronics.persistance.service;

import com.lodo4ka.shopElectronics.persistance.model.Product;
import com.lodo4ka.shopElectronics.persistance.model.Showcase;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();

    void addProduct(Product product);
}
