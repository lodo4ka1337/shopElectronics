package com.lodo4ka.shopElectronics.persistance.repositories;

import com.lodo4ka.shopElectronics.persistance.model.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    Product getProductById(UUID id);
}
