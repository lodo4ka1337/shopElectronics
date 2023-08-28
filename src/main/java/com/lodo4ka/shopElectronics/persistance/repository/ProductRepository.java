package com.lodo4ka.shopElectronics.persistance.repository;

import com.lodo4ka.shopElectronics.persistance.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    List<Product> findAllProductsByShowcaseId(UUID id);

    List<Product> findAllProductsByShowcaseIdAndType(UUID id, String type);

    List<Product> findAllProductsByShowcaseIdAndPriceBetween(UUID id, double price1, double price2);
}
