package com.lodo4ka.shopElectronics.persistance.service;

import com.lodo4ka.shopElectronics.persistance.model.Product;
import com.lodo4ka.shopElectronics.persistance.model.Showcase;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    List<Product> getAllProductsOfShowcase(UUID id);

    List<Product> getAllProductsOfShowcaseFilteredByType(UUID id, String type);

    void addProduct(Product product);

    void deleteProduct(UUID id);

    void updateProduct(Product product);
}
