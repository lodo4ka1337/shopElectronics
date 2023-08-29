package com.lodo4ka.shopElectronics.persistance.repository;

import com.lodo4ka.shopElectronics.persistance.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    Product getProductById(UUID id);
    List<Product> getAllProductsByShowcaseId(UUID id);

    List<Product> getAllProductsByShowcaseIdAndType(UUID id, String type);

    List<Product> getAllProductsByShowcaseIdAndPriceBetween(UUID id, double price1, double price2);
}
