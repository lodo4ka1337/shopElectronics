package com.lodo4ka.shopElectronics.persistance.service;

import com.lodo4ka.shopElectronics.persistance.model.Product;
import com.lodo4ka.shopElectronics.persistance.model.Showcase;
import com.lodo4ka.shopElectronics.persistance.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService{
    ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProductsOfShowcase(UUID id) {
        return productRepository.findAllProductsByShowcaseId(id);
    }

    @Override
    public List<Product> getAllProductsOfShowcaseFilteredByType(UUID id, String type) {
        return productRepository.findAllProductsByShowcaseIdAndType(id, type);
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
    public void updateProduct(Product product) {
        productRepository.save(product);
    }

}
