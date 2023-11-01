package com.lodo4ka.shopElectronics.persistance.model.DTO.mappers;

import com.lodo4ka.shopElectronics.persistance.model.DTO.ProductDTO;
import com.lodo4ka.shopElectronics.persistance.model.entities.Product;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ProductDTOMapper implements Function<Product, ProductDTO> {

    @Override
    public ProductDTO apply(Product product) {
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
}
