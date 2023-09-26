package com.lodo4ka.shopElectronics.persistance.service.impl;

import com.lodo4ka.shopElectronics.persistance.model.Product;
import com.lodo4ka.shopElectronics.persistance.model.QProduct;
import com.lodo4ka.shopElectronics.persistance.repository.ProductRepository;
import com.lodo4ka.shopElectronics.persistance.service.ProductService;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    @Override
    public void addProduct(Product product) {
        productRepository.save(product);
        productRepository.getProductById(product.getId()).
                setDateOfPlacing(new Date(new java.util.Date().getTime()));
        actualizeProduct(product);
    }

    @Transactional
    @Override
    public void deleteProduct(UUID id) {
        productRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void updateProduct(UUID id, Optional<String> name, Optional<String> type, Optional<Double> price, Optional<UUID> showcaseId, Optional<Integer> positionOnShowcase) {
        Product product = productRepository.getProductById(id);
        name.ifPresent(product::setName);
        type.ifPresent(product::setType);
        price.ifPresent(product::setPrice);
        showcaseId.ifPresent(product::setShowcaseId);
        positionOnShowcase.ifPresent(product::setPositionOnShowcase);
        actualizeProduct(product);
    }

    @Override
    public List<Product> getProductsOfShowcase(UUID id, String type, Double price1, Double price2) {
        QProduct product = QProduct.product;
        BooleanBuilder predicates = new BooleanBuilder();

        if (type != null) {
            predicates.and(product.type.eq(type));
        }

        if (price1 != null || price2 != null) {
            if (price1 != null && price2 != null) {
                if (price1 > price2) {
                    predicates.and(product.price.between(price1, price2));
                }
                else {

                }
            }
            else {

            }
        }

        return productRepository.findAll(predicates);
    }

    @Transactional
    @Override
    public void actualizeProduct(Product product) {
        product.setLastUpdate(new Date(new java.util.Date().getTime()));
        productRepository.save(product);
    }

}
