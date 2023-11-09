package com.lodo4ka.shopElectronics.persistance.services.impl;

import com.lodo4ka.shopElectronics.persistance.model.DTO.mappers.ProductDTOMapper;
import com.lodo4ka.shopElectronics.persistance.model.DTO.ProductDTO;
import com.lodo4ka.shopElectronics.persistance.model.DTO.searchRequests.ProductSearchRequest;
import com.lodo4ka.shopElectronics.persistance.model.entities.Product;

import com.lodo4ka.shopElectronics.persistance.model.entities.Product_;
import com.lodo4ka.shopElectronics.persistance.repositories.ProductRepository;
import com.lodo4ka.shopElectronics.persistance.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    private final ProductDTOMapper productDTOMapper;

    private final EntityManager em;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ProductDTOMapper productDTOMapper, EntityManager em) {
        this.productRepository = productRepository;
        this.productDTOMapper = productDTOMapper;
        this.em = em;
    }

    @Override
    public List<ProductDTO> getProductsOfShowcase(ProductSearchRequest productSearchRequest) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
        Root<Product> root = criteriaQuery.from(Product.class);

        List<Predicate> predicates = new ArrayList<>();

        if (productSearchRequest.getShowcaseId() != null)
            predicates.add(
                    criteriaBuilder.equal(
                            root.get(Product_.showcaseId), productSearchRequest.getShowcaseId()));

        if (productSearchRequest.getType() != null)
            predicates.add(
                    criteriaBuilder.equal(
                            root.get(Product_.type), productSearchRequest.getType()));

        Double priceMoreThan = productSearchRequest.getPriceMoreThan();
        Double priceLessThan = productSearchRequest.getPriceLessThan();
        if (priceMoreThan != null || priceLessThan != null) {
            if (priceMoreThan != null)
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(
                        root.get(Product_.price), priceMoreThan));

            if (priceLessThan != null)
                predicates.add(criteriaBuilder.lessThanOrEqualTo(
                        root.get(Product_.price), priceLessThan));
        }

        criteriaQuery.where(
                criteriaBuilder.and(predicates.toArray(new Predicate[0])));
        TypedQuery<Product> query = em.createQuery(criteriaQuery);
        return query.getResultList().stream()
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
