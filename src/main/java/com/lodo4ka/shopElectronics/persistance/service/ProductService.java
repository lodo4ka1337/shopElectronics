package com.lodo4ka.shopElectronics.persistance.service;

import com.lodo4ka.shopElectronics.persistance.model.DTO.ProductDTO;
import com.lodo4ka.shopElectronics.persistance.model.DTO.ProductSearchRequest;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    List<ProductDTO> getProductsOfShowcase(ProductSearchRequest productSearchRequest);

    ProductDTO addProduct(ProductDTO productAddRequest);

    ProductDTO updateProduct(ProductDTO productUpdateRequest);

    void deleteProduct(UUID id);

}
