package com.lodo4ka.shopElectronics.controller;

import com.lodo4ka.shopElectronics.persistance.model.Showcase;
import com.lodo4ka.shopElectronics.persistance.service.ShowcaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class ShowcaseController {
    private ShowcaseService showcaseService;

    @Autowired
    public ShowcaseController(ShowcaseService showcaseService) {
        this.showcaseService = showcaseService;
    }

    public ShowcaseController() {
    }

    @GetMapping("/showcases/get/all")
    public List<Showcase> getAllShowcases() {
        return showcaseService.getAllShowcases();
    }

    @GetMapping("/showcases/get/type/{type}")
    public List<Showcase> getAllShowcasesFilteredByType(@PathVariable String type) {
        return showcaseService.getAllShowcasesFilteredByType(type);
    }

    @GetMapping("/showcases/get/address/{address}")
    public List<Showcase> getAllShowcasesFilteredByAddress(@PathVariable String address) {
        return showcaseService.getAllShowcasesFilteredByAddress(address);
    }

    @GetMapping("/showcases/get/date_period")
    public List<Showcase> getAllShowcasesCreatedBetween(@RequestParam(value = "date1") Date date1, @RequestParam(value = "date2") Date date2) {
        return showcaseService.getAllShowcasesCreatedBetween(date1, date2);
    }

    @PostMapping("/showcases/add")
    public void addShowcase(@RequestBody Showcase showcase) {
        showcaseService.addShowcase(showcase);
    }

    @PutMapping("/showcases/update")
    public void updateShowcase(@RequestBody Showcase showcase) {
        showcaseService.updateShowcase(showcase);
    }

    @DeleteMapping("/showcases/delete/{id}")
    public void deleteShowcaseById(@PathVariable("id") UUID id) {
        showcaseService.deleteShowcaseById(id);
    }
}
