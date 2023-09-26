package com.lodo4ka.shopElectronics.controller;

import com.lodo4ka.shopElectronics.persistance.model.Product;
import com.lodo4ka.shopElectronics.persistance.model.Showcase;
import com.lodo4ka.shopElectronics.persistance.service.ProductService;
import com.lodo4ka.shopElectronics.persistance.service.ShowcaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class ShowcaseController {
    private final ShowcaseService showcaseService;

    private final ProductService productService;

    @Autowired
    public ShowcaseController(ShowcaseService showcaseService, ProductService productService) {
        this.showcaseService = showcaseService;
        this.productService = productService;
    }

    @GetMapping("/showcases/get")
    public List<Showcase> getShowcases(@RequestParam(value = "type", required = false) String type,
                                       @RequestParam(value = "address", required = false) String address,
                                       @RequestParam(value = "crDate1", required = false) Date crDate1,
                                       @RequestParam(value = "crDate2", required = false) Date crDate2,
                                       @RequestParam(value = "actDate1", required = false) Date actDate1,
                                       @RequestParam(value = "actDate2", required = false) Date actDate2) {
        return showcaseService.getShowcases(type, address, crDate1, crDate2, actDate1, actDate2);
    }

    @PostMapping("/showcases/add")
    public void addShowcase(@RequestBody Showcase showcase) {
        showcaseService.addShowcase(showcase);
    }

    @PutMapping("/showcases/update")
    public void updateShowcase(@RequestParam(value = "id", required = true) UUID id,
                               @RequestParam(value = "name", required = false) Optional<String> name,
                               @RequestParam(value = "address", required = false) Optional<String> address,
                               @RequestParam(value = "type", required = false) Optional<String> type) {
        showcaseService.updateShowcase(id, name, address, type);
    }

    @DeleteMapping("/showcases/delete")
    public void deleteShowcase(@RequestParam(value = "id") UUID id) {
        showcaseService.deleteShowcaseById(id);
    }

    @GetMapping("/products/get")
    List<Product> getProductsOfShowcase(@RequestParam(value = "showcaseId") UUID id,
                                        @RequestParam(value = "type", required = false) String type,
                                        @RequestParam(value = "price1", required = false) Double price1,
                                        @RequestParam(value = "price2", required = false) Double price2) {
        return productService.getProductsOfShowcase(id, type, price1, price2);
    }

    @PostMapping("/products/add")
    void addProduct(@RequestBody Product product) {
        productService.addProduct(product);
        showcaseService.actualizeShowcaseById(product.getShowcaseId());
    }

    @DeleteMapping("/products/delete")
    void deleteProduct(@RequestParam(value = "id") UUID id) {
        productService.deleteProduct(id);
    }

    @PutMapping("/products/update")
    void updateProduct(@RequestParam(value = "id") UUID id,
                       @RequestParam(value = "name", required = false) Optional<String> name,
                       @RequestParam(value = "type", required = false) Optional<String> type,
                       @RequestParam(value = "price", required = false) Optional<Double> price,
                       @RequestParam(value = "showcaseId", required = false) Optional<UUID> showcaseId,
                       @RequestParam(value = "positionOnShowcase", required = false) Optional<Integer> positionOnShowcase) {
        productService.updateProduct(id, name, type, price, showcaseId, positionOnShowcase);
    }

}