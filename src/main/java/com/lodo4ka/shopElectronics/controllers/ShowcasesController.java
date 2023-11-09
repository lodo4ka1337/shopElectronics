package com.lodo4ka.shopElectronics.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.lodo4ka.shopElectronics.persistance.model.DTO.interfaces.Details;
import com.lodo4ka.shopElectronics.persistance.model.DTO.interfaces.Exists;
import com.lodo4ka.shopElectronics.persistance.model.DTO.interfaces.New;
import com.lodo4ka.shopElectronics.persistance.model.DTO.ProductDTO;
import com.lodo4ka.shopElectronics.persistance.model.DTO.searchRequests.ProductSearchRequest;
import com.lodo4ka.shopElectronics.persistance.model.DTO.ShowcaseDTO;
import com.lodo4ka.shopElectronics.persistance.model.DTO.searchRequests.ShowcaseSearchRequest;
import com.lodo4ka.shopElectronics.persistance.services.ProductService;
import com.lodo4ka.shopElectronics.persistance.services.ShowcaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Validated
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
    public List<ShowcaseDTO> getShowcases(@Valid ShowcaseSearchRequest showcaseSearchRequest) {
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
    public String  deleteShowcase(@RequestParam(value = "id") UUID id) {
        showcaseService.deleteShowcaseById(id);
        return "Showcase with id = " + id + " deleted successfully.";
    }

    @GetMapping("/products/get")
    public List<ProductDTO> getProductsOfShowcase(@Valid ProductSearchRequest productSearchRequest) {
        return productService.getProductsOfShowcase(productSearchRequest);
    }

    @PostMapping("/products/add")
    public ProductDTO addProduct(@Validated(New.class) @RequestBody ProductDTO productAddRequest) {
        return productService.addProduct(productAddRequest);
    }

    @PutMapping("/products/update")
    public ProductDTO updateProduct(@Validated(Exists.class) ProductDTO productUpdateRequest) {
        return productService.updateProduct(productUpdateRequest);
    }

    @DeleteMapping("/products/delete")
    public String deleteProduct(@RequestParam(value = "id") UUID id) {
        productService.deleteProduct(id);
        return "Product with id = " + id + " deleted successfully.";
    }

}