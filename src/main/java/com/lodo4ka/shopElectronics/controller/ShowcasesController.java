package com.lodo4ka.shopElectronics.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.lodo4ka.shopElectronics.persistance.model.DTO.Interfaces.Details;
import com.lodo4ka.shopElectronics.persistance.model.DTO.Interfaces.Exists;
import com.lodo4ka.shopElectronics.persistance.model.DTO.Interfaces.New;
import com.lodo4ka.shopElectronics.persistance.model.DTO.ProductDTO;
import com.lodo4ka.shopElectronics.persistance.model.DTO.ProductSearchRequest;
import com.lodo4ka.shopElectronics.persistance.model.DTO.ShowcaseDTO;
import com.lodo4ka.shopElectronics.persistance.model.DTO.ShowcaseSearchRequest;
import com.lodo4ka.shopElectronics.persistance.model.Product;
import com.lodo4ka.shopElectronics.persistance.service.ProductService;
import com.lodo4ka.shopElectronics.persistance.service.ShowcaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class ShowcasesController {
    private final ShowcaseService showcaseService;

    private final ProductService productService;

    @Autowired
    public ShowcasesController(ShowcaseService showcaseService, ProductService productService) {
        this.showcaseService = showcaseService;
        this.productService = productService;
    }

    @GetMapping("/showcases/get")
    @JsonView({Details.class})
    public List<ShowcaseDTO> getShowcases(@Validated ShowcaseSearchRequest showcaseSearchRequest) {
        return showcaseService.getShowcases(showcaseSearchRequest);
    }

    @PostMapping("/showcases/add")
    @JsonView({Details.class})
    public ShowcaseDTO addShowcase(@Validated(New.class) @RequestBody ShowcaseDTO showcaseAddRequest) {
        return showcaseService.addShowcase(showcaseAddRequest);
    }

    @PutMapping("/showcases/update")
    @JsonView({Details.class})
    public ShowcaseDTO updateShowcase(@Validated(Exists.class) ShowcaseDTO showcaseUpdateRequest) {
        return showcaseService.updateShowcase(showcaseUpdateRequest);
    }

    @DeleteMapping("/showcases/delete")
    public void deleteShowcase(@RequestParam(value = "id") UUID id) {
        showcaseService.deleteShowcaseById(id);
    }

    @GetMapping("/products/get")
    List<ProductDTO> getProductsOfShowcase(@Validated ProductSearchRequest productSearchRequest) {
        return productService.getProductsOfShowcase(productSearchRequest);
    }

    @PostMapping("/products/add")
    ProductDTO addProduct(@Validated(New.class) @RequestBody ProductDTO productAddRequest) {
        return productService.addProduct(productAddRequest);
    }

    @PutMapping("/products/update")
    ProductDTO updateProduct(@Validated(Exists.class) @RequestBody ProductDTO productUpdateRequest) {
        return productService.updateProduct(productUpdateRequest);
    }

    @DeleteMapping("/products/delete")
    void deleteProduct(@RequestParam(value = "id") UUID id) {
        productService.deleteProduct(id);
    }

}