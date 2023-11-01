package com.lodo4ka.shopElectronics.persistance.services.impl;

import com.lodo4ka.shopElectronics.persistance.model.DTO.mappers.ProductDTOMapper;
import com.lodo4ka.shopElectronics.persistance.model.DTO.ProductDTO;
import com.lodo4ka.shopElectronics.persistance.model.DTO.searchRequests.ProductSearchRequest;
import com.lodo4ka.shopElectronics.persistance.model.entities.Product;

import com.lodo4ka.shopElectronics.persistance.model.entities.QProduct;
import com.lodo4ka.shopElectronics.persistance.repositories.ProductRepository;
import com.lodo4ka.shopElectronics.persistance.services.ProductService;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    private final ProductDTOMapper productDTOMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ProductDTOMapper productDTOMapper) {
        this.productRepository = productRepository;
        this.productDTOMapper = productDTOMapper;
    }

    @Override
    public List<ProductDTO> getProductsOfShowcase(ProductSearchRequest productSearchRequest) {
        QProduct product = QProduct.product;
        BooleanBuilder predicates = new BooleanBuilder();

        predicates.and(product.showcaseId.eq(productSearchRequest.getShowcaseId()));

        if (productSearchRequest.getType() != null) {
            predicates.and(product.type.eq(productSearchRequest.getType()));
        }

        Double price1 = productSearchRequest.getPrice1();
        Double price2 = productSearchRequest.getPrice2();
        if (price1 != null || price2 != null) {
            if (price1 != null && price2 != null) {
                if (price1 < price2) {
                    predicates.and(product.price.between(price1, price2));
                }
                else {

                }
            }
            else {

            }
        }

        return productRepository.findAll(predicates)
                .stream()
                .map(productDTOMapper)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public ProductDTO addProduct(ProductDTO productAddRequest) {
        Product product = new Product(
                productAddRequest.getName(),
                productAddRequest.getType(),
                productAddRequest.getPrice(),
                productAddRequest.getShowcaseId(),
                productAddRequest.getPositionOnShowcase()
        );
        productRepository.save(product);

        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getType(),
                product.getPrice(),
                product.getShowcaseId(),
                product.getPositionOnShowcase(),
                product.getDateOfPlacing(),
                product.getLastUpdate()
        );
    }

    @Transactional
    @Override
    public ProductDTO updateProduct(ProductDTO productUpdateRequest) {
        Product product = productRepository.getProductById(productUpdateRequest.getId());

        if (productUpdateRequest.getName() != null) {
            product.setName(productUpdateRequest.getName());
        }

        if (productUpdateRequest.getType() != null) {
            product.setType(productUpdateRequest.getType());
        }

        if (productUpdateRequest.getPrice() != null) {
            product.setPrice(productUpdateRequest.getPrice());
        }

        if (productUpdateRequest.getShowcaseId() != null) {
            product.setShowcaseId(productUpdateRequest.getShowcaseId());
        }

        if (productUpdateRequest.getPositionOnShowcase() != null) {
            product.setPositionOnShowcase(productUpdateRequest.getPositionOnShowcase());
        }
        productRepository.save(product);

        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getType(),
                product.getPrice(),
                product.getShowcaseId(),
                product.getPositionOnShowcase(),
                product.getDateOfPlacing(),
                product.getLastUpdate()
        );
    }

    @Transactional
    @Override
    public void deleteProduct(UUID id) {
        productRepository.deleteById(id);
    }

}
