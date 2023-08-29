package com.lodo4ka.shopElectronics.persistance.service;

import com.lodo4ka.shopElectronics.persistance.model.Product;
import com.lodo4ka.shopElectronics.persistance.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProductsOfShowcase(UUID id) {
        return productRepository.getAllProductsByShowcaseId(id);
    }

    @Override
    public List<Product> getAllProductsOfShowcaseFilteredByType(UUID id, String type) {
        return productRepository.getAllProductsByShowcaseIdAndType(id, type);
    }

    @Override
    public List<Product> getAllProductsOfShowcaseWithPriceBetween(UUID id, double price1, double price2) {
        return productRepository.getAllProductsByShowcaseIdAndPriceBetween(id, price1, price2);
    }

    @Transactional
    @Override
    public void addProduct(Product product) {
        productRepository.save(product);
    }

    @Transactional
    @Override
    public void deleteProduct(UUID id) {
        productRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void updateProduct(UUID id, Optional<String> name, Optional<String> type, Optional<Double> price, Optional<UUID> showcaseId, Optional<Integer> positionOnShowcase, Optional<Date> dateOfPlacing) {
        Product product = productRepository.getProductById(id);
        name.ifPresent(product::setName);
        type.ifPresent(product::setType);
        price.ifPresent(product::setPrice);
        showcaseId.ifPresent(product::setShowcaseId);
        positionOnShowcase.ifPresent(product::setPositionOnShowcase);
        dateOfPlacing.ifPresent(product::setDateOfPlacing);
        actualizeProduct(product);
    }

    @Transactional
    @Override
    public void actualizeProduct(Product product) {
        product.setLastUpdate(new Date(new java.util.Date().getTime()));
        productRepository.save(product);
    }

}
