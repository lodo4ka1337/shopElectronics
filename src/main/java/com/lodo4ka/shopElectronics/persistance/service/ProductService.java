package com.lodo4ka.shopElectronics.persistance.service;

import com.lodo4ka.shopElectronics.persistance.model.Product;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductService {

    void addProduct(Product product);

    void deleteProduct(UUID id);

    void actualizeProduct(Product product);

    void updateProduct(UUID id, Optional<String> name, Optional<String> type, Optional<Double> price, Optional<UUID> showcaseId, Optional<Integer> positionOnShowcase);

    List<Product> getProductsOfShowcase(UUID id, String type, Double price1, Double price2);
}
