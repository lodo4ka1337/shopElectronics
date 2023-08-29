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

    @GetMapping("/showcases/get/all")
    public List<Showcase> getAllShowcases() {
        return showcaseService.getAllShowcases();
    }

    @GetMapping("/showcases/get/type")
    public List<Showcase> getAllShowcasesFilteredByType(@RequestParam(value = "type") String type) {
        return showcaseService.getAllShowcasesFilteredByType(type);
    }

    @GetMapping("/showcases/get/address")
    public List<Showcase> getAllShowcasesFilteredByAddress(@RequestParam(value = "address") String address) {
        return showcaseService.getAllShowcasesFilteredByAddress(address);
    }

    @GetMapping("/showcases/get/creation_date_period")
    public List<Showcase> getAllShowcasesCreatedBetween(@RequestParam(value = "date1") Date date1,
                                                        @RequestParam(value = "date2") Date date2) {
        return showcaseService.getAllShowcasesCreatedBetween(date1, date2);
    }

    @GetMapping("/showcases/get/last_update_period")
    public List<Showcase> getAllShowcasesActualizedBetween(@RequestParam(value = "date1") Date date1,
                                                           @RequestParam(value = "date2") Date date2) {
        return showcaseService.getAllShowcasesActualizedBetween(date1, date2);
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
    public void deleteShowcaseById(@RequestParam(value = "id") UUID id) {
        showcaseService.deleteShowcaseById(id);
    }

    @GetMapping("/products/get/all")
    List<Product> getAllProductsOfShowcase(@RequestParam(value = "showcaseId") UUID id) {
        return productService.getAllProductsOfShowcase(id);
    }

    @GetMapping("/products/get/type")
    List<Product> getAllProductsOfShowcaseFilteredByType(@RequestParam(value = "showcaseId") UUID id,
                                                         @RequestParam(value = "type") String type) {
        return productService.getAllProductsOfShowcaseFilteredByType(id, type);
    }

    @GetMapping("/products/get/price")
    List<Product> getAllProductsOfShowcaseFilteredByPriceBetween(@RequestParam(value = "showcaseId") UUID id,
                                                                 @RequestParam(value = "price1") double price1,
                                                                 @RequestParam(value = "price2") double price2) {
        return productService.getAllProductsOfShowcaseWithPriceBetween(id, price1, price2);
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
    void updateProduct(@RequestParam(value = "id", required = true) UUID id,
                       @RequestParam(value = "name", required = false) Optional<String> name,
                       @RequestParam(value = "type", required = false) Optional<String> type,
                       @RequestParam(value = "price", required = false) Optional<Double> price,
                       @RequestParam(value = "showcaseId", required = false) Optional<UUID> showcaseId,
                       @RequestParam(value = "positionOnShowcase", required = false) Optional<Integer> positionOnShowcase) {
        productService.updateProduct(id, name, type, price, showcaseId, positionOnShowcase);
    }

}