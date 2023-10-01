package com.lodo4ka.shopElectronics.persistance.repository;

import com.lodo4ka.shopElectronics.persistance.model.Product;
import com.querydsl.core.types.Predicate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID>, QuerydslPredicateExecutor<Product> {

    Product getProductById(UUID id);

    @NonNull
    List<Product> findAll(@NonNull Predicate predicate);
}
